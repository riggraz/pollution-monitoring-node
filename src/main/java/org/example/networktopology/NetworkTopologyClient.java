package org.example.networktopology;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.beans.Node;
import org.example.beans.NodeList;
import org.example.proto.AddNodeRequest;
import org.example.proto.NetworkTopologyServiceGrpc;
import org.example.proto.Response;

import java.util.concurrent.TimeUnit;

public class NetworkTopologyClient implements Runnable {
    private Node node;

    public NetworkTopologyClient(Node node) {
        this.node = node;
    }

    @Override
    public void run() {
        for (Node otherNode : NodeList.getInstance().getList()) {
            if (otherNode.getId().equals(node.getId())) continue;

            (new Thread(new Runnable() {
                @Override
                public void run() {
                    ManagedChannel channel = ManagedChannelBuilder
                            .forAddress(otherNode.getIp(), otherNode.getPort())
                            .usePlaintext()
                            .build();

                    NetworkTopologyServiceGrpc.NetworkTopologyServiceBlockingStub stub = NetworkTopologyServiceGrpc.newBlockingStub(channel);

                    Response response = stub.addNode(
                            AddNodeRequest.newBuilder()
                                    .setId(node.getId().toString())
                                    .setIp(node.getIp())
                                    .setPort(node.getPort())
                                    .build()
                    );

                    System.out.println("I contacted " + otherNode + " to let it know I'm here! Response code was: " + response.getSuccess());

                    channel.shutdown();
                }
            })).start();
        }
    }
}
