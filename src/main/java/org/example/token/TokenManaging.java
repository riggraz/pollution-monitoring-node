package org.example.token;

import com.google.gson.Gson;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.beans.LocalStatisticList;
import org.example.beans.Node;
import org.example.beans.NodeList;
import org.example.beans.ThisNode;
import org.example.proto.MeasurementMessage;
import org.example.proto.SendTokenRequest;
import org.example.proto.SendTokenResponse;
import org.example.proto.TokenServiceGrpc;
import org.example.simulator.Measurement;
import org.example.utils.Constants;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TokenManaging implements Runnable {
    private List<MeasurementMessage> measurementList;
    private List<String> participantIds;

    private ManagedChannel channel;

    public TokenManaging(List<MeasurementMessage> measurementList, List<String> participantIds) {
        this.measurementList = measurementList;
        this.participantIds = participantIds;
    }

    @Override
    public void run() {
        if (participantIds.contains(ThisNode.getInstance().getNode().getId().toString())) {
            List<Measurement> localStatistics = LocalStatisticList.getInstance().getList();

            if (localStatistics.size() > 0) {
                // add local statistic to token
                measurementList.add(
                        MeasurementMessage
                                .newBuilder()
                                .setValue(localStatistics.get(0).getValue())
                                .setTimestamp(localStatistics.get(0).getTimestamp())
                                .build()
                );

                // remove the statistic locally
                LocalStatisticList.getInstance().deleteOldestMeasurement();

                System.out.println("Added local statistic to token (" + localStatistics.size() + " left in buffer)");

                // remove this node id from participant ids
                participantIds.remove(ThisNode.getInstance().getNode().getId().toString());
            }
        }

        if (participantIds.size() == 0) {
            // all statistics are in the token, so we calc a global statistic
            // and send it to the gateway
            Measurement globalStatistic = calculateGlobalStatistic();
            sendGlobalStatisticToGateway(globalStatistic);

            // now i need to reset the token
            // new list of participants
            for (Node n : NodeList.getInstance().getList()) {
                participantIds.add(n.getId().toString());
            }

            // empty measurements
            measurementList.clear();

            forwardToken();

        } else {
            forwardToken();
        }

        channel.shutdown();
    }

    private void forwardToken() {
        Node nextNode = null;

        while (nextNode == null) {
            if (participantIds.size() == 0) {
                sendGlobalStatisticToGateway(calculateGlobalStatistic());
                return;
            }

            String nextNodeId = participantIds.get(0);
            for (Node node : NodeList.getInstance().getList()) {
                if (node.getId().toString().equals(nextNodeId)) {
                    nextNode = node;
                }
            }

            if (nextNode == null) {
                // the node exited network while it was a participant
                participantIds.remove(0);
            }
        }

        channel = ManagedChannelBuilder.forAddress(nextNode.getIp(), nextNode.getPort())
                .usePlaintext()
                .build();

        System.out.println(nextNode);

        TokenServiceGrpc.TokenServiceBlockingStub stub
                = TokenServiceGrpc.newBlockingStub(channel);

        // send the token to the next node
        SendTokenResponse sendTokenResponse = stub.sendToken(SendTokenRequest
                .newBuilder()
                .addAllLocalStatistics(measurementList)
                .addAllParticipantIds(participantIds)
                .build());
    }

    private Measurement calculateGlobalStatistic() {
        double average = 0.0;
        for (MeasurementMessage m : measurementList) {
            average += m.getValue();
        }
        average /= measurementList.size();

        Measurement globalStatistic = new Measurement(
                "pm10-123",
                "PM10",
                average,
                measurementList.get(measurementList.size()-1).getTimestamp()
        );

        return globalStatistic;
    }

    private void sendGlobalStatisticToGateway(Measurement globalStatistic) {
        try {
            Gson gson = new Gson();
            URL url = new URL(Constants.GATEWAY_ADDRESS + "/measurements");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String nodeJson = gson.toJson(globalStatistic);
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = nodeJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            if (connection.getResponseCode() == 204) {
                System.out.println("I sent a global statistic to the gateway");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
