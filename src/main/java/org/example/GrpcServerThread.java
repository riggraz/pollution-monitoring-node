package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.beans.ThisNode;
import org.example.networktopology.NetworkTopologyService;
import org.example.token.TokenService;

import java.io.IOException;

public class GrpcServerThread implements Runnable {
    @Override
    public void run() {
        Server server = ServerBuilder
                .forPort(ThisNode.getInstance().getNode().getPort())
                .addService(new NetworkTopologyService())
                .addService(new TokenService())
                .build();

        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            System.out.println("Shutting down grpc server...");
            server.shutdown();
        }
    }
}
