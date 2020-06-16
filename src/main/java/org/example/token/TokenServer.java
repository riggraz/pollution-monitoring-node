package org.example.token;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.beans.ThisNode;

import java.io.IOException;

public class TokenServer implements Runnable {
    @Override
    public void run() {
        Server server = ServerBuilder
            .forPort(ThisNode.getInstance().getNode().getPort())
            .addService(new TokenService()).build();

        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            System.out.println("Shutting down token server...");
            server.shutdown();
        }
    }
}
