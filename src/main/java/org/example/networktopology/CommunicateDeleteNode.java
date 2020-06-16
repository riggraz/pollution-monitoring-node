package org.example.networktopology;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.example.beans.Node;
import org.example.beans.NodeList;
import org.example.beans.ThisNode;
import org.example.proto.DeleteNodeRequest;
import org.example.proto.NetworkTopologyServiceGrpc;
import org.example.proto.Response;

import java.util.List;

public class CommunicateDeleteNode implements Runnable {
    @Override
    public void run() {
        Node node = ThisNode.getInstance().getNode();
        List<Node> nodeList = NodeList.getInstance().getList();

        for (Node otherNode : nodeList) {
            if (otherNode.getId().equals(node.getId())) continue;

            (new Thread(new Runnable() {
                @Override
                public void run() {
                    ManagedChannel channel = ManagedChannelBuilder
                            .forAddress(otherNode.getIp(), otherNode.getPort())
                            .usePlaintext()
                            .build();

                    NetworkTopologyServiceGrpc.NetworkTopologyServiceBlockingStub stub = NetworkTopologyServiceGrpc.newBlockingStub(channel);

                    try {
                        Response response = stub.deleteNode(
                                DeleteNodeRequest.newBuilder()
                                        .setId(node.getId().toString())
                                        .build()
                        );

                        System.out.println("I contacted " + otherNode + " to let it know I'm going away! Response code was: " + response.getCode());
                    } catch (StatusRuntimeException e) {
                        if (e.getStatus().getCode().toString() == "UNAVAILABLE") {
                            // since in our hypothesis nodes cannot malfunction
                            // if it is unavailable it means that it exited the network
                            NodeList.getInstance().deleteNode(otherNode.getId());
                            System.out.println(otherNode + " exited network so I remove it from list");
                        }
                    } finally {
                        channel.shutdown();
                    }
                }
            })).start();
        }
    }
}
