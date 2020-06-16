package org.example.token;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.beans.Node;
import org.example.beans.NodeList;
import org.example.beans.ThisNode;
import org.example.proto.SendTokenRequest;
import org.example.proto.SendTokenResponse;
import org.example.proto.TokenServiceGrpc;

import java.util.ArrayList;
import java.util.List;

public class TokenModule {
    private static Thread serverThread;

    public static void launchServer() {
        serverThread = new Thread(new TokenServer());
        serverThread.start();
    }

    public static void stopServer() {
        serverThread.interrupt();
    }

    public static void generateToken() {
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress(
                ThisNode.getInstance().getNode().getIp(),
                ThisNode.getInstance().getNode().getPort()
            )
            .usePlaintext()
            .build();

        TokenServiceGrpc.TokenServiceBlockingStub stub = TokenServiceGrpc.newBlockingStub(channel);

        SendTokenResponse sendTokenResponse = stub.sendToken(
                SendTokenRequest
                        .newBuilder()
                        .addAllParticipantIds(getAllParticipantIds())
                        .build()
        );

        channel.shutdown();

        System.out.println("Token generated");
    }

    private static List<String> getAllParticipantIds() {
        List<String> participantIds = new ArrayList<String>();

        for (Node n : NodeList.getInstance().getList()) {
            participantIds.add(n.getId().toString());
        }

        return participantIds;
    }
}
