package org.example.networktopology;

import io.grpc.stub.StreamObserver;
import org.example.beans.Node;
import org.example.beans.NodeList;
import org.example.proto.AddNodeRequest;
import org.example.proto.NetworkTopologyServiceGrpc;
import org.example.proto.Response;

import java.util.UUID;

public class NetworkTopologyService extends NetworkTopologyServiceGrpc.NetworkTopologyServiceImplBase {
    @Override
    public void addNode(AddNodeRequest request, StreamObserver<Response> responseObserver) {
        Node newNode = new Node();
        newNode.setId(UUID.fromString(request.getId()));
        newNode.setIp(request.getIp());
        newNode.setPort(request.getPort());

        // add the newNode to the list of nodes
        NodeList.getInstance().addNode(newNode);
        System.out.println("Added new node to: " + NodeList.getInstance().getList());

        Response response = Response
                .newBuilder()
                .setSuccess(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
