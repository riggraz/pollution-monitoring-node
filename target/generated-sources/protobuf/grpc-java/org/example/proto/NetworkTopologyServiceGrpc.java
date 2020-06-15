package org.example.proto;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: NetworkTopologyService.proto")
public final class NetworkTopologyServiceGrpc {

  private NetworkTopologyServiceGrpc() {}

  public static final String SERVICE_NAME = "org.example.proto.NetworkTopologyService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.example.proto.AddNodeRequest,
      org.example.proto.Response> METHOD_ADD_NODE =
      io.grpc.MethodDescriptor.<org.example.proto.AddNodeRequest, org.example.proto.Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "org.example.proto.NetworkTopologyService", "addNode"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.example.proto.AddNodeRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.example.proto.Response.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.example.proto.DeleteNodeRequest,
      org.example.proto.Response> METHOD_DELETE_NODE =
      io.grpc.MethodDescriptor.<org.example.proto.DeleteNodeRequest, org.example.proto.Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "org.example.proto.NetworkTopologyService", "deleteNode"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.example.proto.DeleteNodeRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              org.example.proto.Response.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NetworkTopologyServiceStub newStub(io.grpc.Channel channel) {
    return new NetworkTopologyServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NetworkTopologyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NetworkTopologyServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NetworkTopologyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NetworkTopologyServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NetworkTopologyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addNode(org.example.proto.AddNodeRequest request,
        io.grpc.stub.StreamObserver<org.example.proto.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_NODE, responseObserver);
    }

    /**
     */
    public void deleteNode(org.example.proto.DeleteNodeRequest request,
        io.grpc.stub.StreamObserver<org.example.proto.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_NODE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_NODE,
            asyncUnaryCall(
              new MethodHandlers<
                org.example.proto.AddNodeRequest,
                org.example.proto.Response>(
                  this, METHODID_ADD_NODE)))
          .addMethod(
            METHOD_DELETE_NODE,
            asyncUnaryCall(
              new MethodHandlers<
                org.example.proto.DeleteNodeRequest,
                org.example.proto.Response>(
                  this, METHODID_DELETE_NODE)))
          .build();
    }
  }

  /**
   */
  public static final class NetworkTopologyServiceStub extends io.grpc.stub.AbstractStub<NetworkTopologyServiceStub> {
    private NetworkTopologyServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NetworkTopologyServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NetworkTopologyServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NetworkTopologyServiceStub(channel, callOptions);
    }

    /**
     */
    public void addNode(org.example.proto.AddNodeRequest request,
        io.grpc.stub.StreamObserver<org.example.proto.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_NODE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteNode(org.example.proto.DeleteNodeRequest request,
        io.grpc.stub.StreamObserver<org.example.proto.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_NODE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NetworkTopologyServiceBlockingStub extends io.grpc.stub.AbstractStub<NetworkTopologyServiceBlockingStub> {
    private NetworkTopologyServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NetworkTopologyServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NetworkTopologyServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NetworkTopologyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.example.proto.Response addNode(org.example.proto.AddNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_NODE, getCallOptions(), request);
    }

    /**
     */
    public org.example.proto.Response deleteNode(org.example.proto.DeleteNodeRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_NODE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NetworkTopologyServiceFutureStub extends io.grpc.stub.AbstractStub<NetworkTopologyServiceFutureStub> {
    private NetworkTopologyServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NetworkTopologyServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NetworkTopologyServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NetworkTopologyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.proto.Response> addNode(
        org.example.proto.AddNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_NODE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.example.proto.Response> deleteNode(
        org.example.proto.DeleteNodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_NODE, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_NODE = 0;
  private static final int METHODID_DELETE_NODE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NetworkTopologyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NetworkTopologyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_NODE:
          serviceImpl.addNode((org.example.proto.AddNodeRequest) request,
              (io.grpc.stub.StreamObserver<org.example.proto.Response>) responseObserver);
          break;
        case METHODID_DELETE_NODE:
          serviceImpl.deleteNode((org.example.proto.DeleteNodeRequest) request,
              (io.grpc.stub.StreamObserver<org.example.proto.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class NetworkTopologyServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.example.proto.NetworkTopologyServiceOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NetworkTopologyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NetworkTopologyServiceDescriptorSupplier())
              .addMethod(METHOD_ADD_NODE)
              .addMethod(METHOD_DELETE_NODE)
              .build();
        }
      }
    }
    return result;
  }
}
