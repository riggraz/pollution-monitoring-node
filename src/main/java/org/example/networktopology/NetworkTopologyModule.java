package org.example.networktopology;

public class NetworkTopologyModule {
    public static void communicateAddNode() {
        // Communicate to all other nodes that this node is entering the network
        new Thread(new CommunicateAddNode()).start();
    }

    public static void communicateDeleteNode() {
        // Communicate to all other nodes that this node is exiting the network
        new Thread(new CommunicateDeleteNode()).start();
    }
}
