package org.example.networktopology;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.beans.Node;
import org.example.beans.ThisNode;

import java.io.IOException;

public class NetworkTopologyServer implements Runnable {
    @Override
    public void run() {
        Server server = ServerBuilder
                .forPort(ThisNode.getInstance().getNode().getPort())
                .addService(new NetworkTopologyService())
                .build();

        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            System.out.println("Shutting down server...");
            server.shutdown();
        }
    }
}
