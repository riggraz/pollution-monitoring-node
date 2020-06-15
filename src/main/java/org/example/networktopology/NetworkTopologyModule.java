package org.example.networktopology;

import org.example.beans.Node;

public class NetworkTopologyModule {
    public static void launchClientAndServer(Node node) {
        // Start server to listen to incoming messages
        Thread serverThread = new Thread(new NetworkTopologyServer(node));
        serverThread.start();

        // Communicate to all other nodes that this node is entering the network
        Thread clientThread = new Thread(new NetworkTopologyClient(node));
        clientThread.start();
    }
}
