package org.example.token;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
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
import java.util.ArrayList;
import java.util.List;

public class TokenService extends TokenServiceGrpc.TokenServiceImplBase {
    @Override
    public void sendToken(SendTokenRequest request, StreamObserver<SendTokenResponse> responseObserver) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Received token");

        // Get data from received token
        List<MeasurementMessage> measurementList = new ArrayList<MeasurementMessage>(request.getLocalStatisticsList());
        List<String> participantIds = new ArrayList<String>(request.getParticipantIdsList());

        // Launch a new thread to send the token to the next node
        (new Thread(new TokenManaging(measurementList, participantIds))).start();

        // Respond to previous node
        responseObserver.onNext(SendTokenResponse.newBuilder()
                .setCode(0)
                .build()
        );
        responseObserver.onCompleted();
    }
}
