package org.example.networktopology;

public class NetworkTopologyModule {
    private static Thread serverThread;

    public static void launchServer() {
        // Start server to listen to incoming messages
        serverThread = new Thread(new NetworkTopologyServer());
        serverThread.start();
    }

    public static void stopServer() {
        serverThread.interrupt();
    }

    public static void communicateAddNode() {
        // Communicate to all other nodes that this node is entering the network
        new Thread(new CommunicateAddNode()).start();
    }

    public static void communicateDeleteNode() {
        // Communicate to all other nodes that this node is exiting the network
        new Thread(new CommunicateDeleteNode()).start();
    }
}
