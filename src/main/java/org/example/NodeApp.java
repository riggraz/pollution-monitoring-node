package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.beans.Node;
import org.example.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class NodeApp {
    private Node node;
    private List<Node> nodeList;

    private Scanner in;
    private Gson gson;

    public NodeApp() {
        node = new Node();
        nodeList = new ArrayList<Node>();
        in = new Scanner(System.in);
        gson = new Gson();

        getNodeAttributes();
        addNodeToGateway();
    }

    private void getNodeAttributes() {
        node.setId(UUID.randomUUID());
        System.out.println("Node id: " + node.getId());

        node.setIp("localhost");
        System.out.println("Node ip: " + node.getIp());

        System.out.print("Insert port number: ");
        node.setPort(in.nextInt());
        System.out.println("Node port: " + node.getPort());

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
            String nodeJson = gson.toJson(node);
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = nodeJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            nodeList = gson.fromJson(
                    new InputStreamReader((InputStream) connection.getContent()),
                    new TypeToken<ArrayList<Node>>(){}.getType()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
