package org.example.networktopology;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.beans.Node;

import java.io.IOException;

public class NetworkTopologyServer implements Runnable {
    private Node node;

    public NetworkTopologyServer(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        Server server = ServerBuilder
                .forPort(node.getPort())
                .addService(new NetworkTopologyService())
                .build();

        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
