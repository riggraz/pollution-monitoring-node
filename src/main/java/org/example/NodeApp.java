package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.beans.Node;
import org.example.beans.NodeList;
import org.example.beans.ThisNode;
import org.example.networktopology.NetworkTopologyModule;
import org.example.networktopology.NetworkTopologyService;
import org.example.token.TokenModule;
import org.example.token.TokenService;
import org.example.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class NodeApp extends Thread {
    private Scanner in;
    private Gson gson;

    public NodeApp() {
        in = new Scanner(System.in);
        gson = new Gson();
    }

    @Override
    public void run() {
        // Read node attributes from stdin
        getNodeAttributes();

        // Add node to gateway
        addNodeToGateway();

        // Run gRPC Server to handle both NetworkTopologyService and TokenService requests
        Thread grpcServerThread = new Thread(new GrpcServerThread());
        grpcServerThread.start();

        // If this node is the only one, then it must generate the token
        if (NodeList.getInstance().getList().size() == 1) TokenModule.generateToken();

        /*try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // Communicate to all other nodes that this node has entered the network
        NetworkTopologyModule.communicateAddNode();

        // Run a thread to listen to stdin (used to quit the node)
        Thread inputReaderThread = new Thread(new InputReaderThread());
        inputReaderThread.start();

        // Run a thread to sense data through simulator
        Thread sensorThread = new Thread(new SensorThread());
        sensorThread.start();

        try {
            // Wait for the input thread to finish, then throw down the node
            inputReaderThread.join();

            ThisNode.getInstance().setIsExiting(true);

            //Thread.sleep(15000);

            // Communicate to all other nodes that this node is leaving the network
            NetworkTopologyModule.communicateDeleteNode();

            // Delete node from gateway
            deleteNodeFromGateway();

            // Interrupt remaining threads
            sensorThread.interrupt();
            grpcServerThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Successfully exited network");
        }
    }

    private void getNodeAttributes() {
        Node node = new Node();

        node.setId(UUID.randomUUID());
        System.out.println("Node id: " + node.getId());

        node.setIp("localhost");
        System.out.println("Node ip: " + node.getIp());

        System.out.print("Insert port number: ");
        node.setPort(in.nextInt());
        System.out.println("Node port: " + node.getPort());

        ThisNode.getInstance().setNode(node);

        System.out.println("Node created: " + node);
    }

    private void addNodeToGateway() {
        try {
            URL url = new URL(Constants.GATEWAY_ADDRESS + "/nodes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String nodeJson = gson.toJson(ThisNode.getInstance().getNode());
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = nodeJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            NodeList.getInstance().setList(gson.fromJson(
                    new InputStreamReader((InputStream) connection.getContent()),
                    new TypeToken<ArrayList<Node>>(){}.getType()
            ));

            System.out.println("Received this list from gateway: " + NodeList.getInstance().getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteNodeFromGateway() {
        URL url = null;
        try {
            url = new URL(Constants.GATEWAY_ADDRESS + "/nodes/" + ThisNode.getInstance().getNode().getId());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.connect();
            if (connection.getResponseCode() == 204) {
                System.out.println("Deleted " + ThisNode.getInstance().getNode() + " from gateway");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
