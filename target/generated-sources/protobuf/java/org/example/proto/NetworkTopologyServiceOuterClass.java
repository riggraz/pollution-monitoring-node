// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NetworkTopologyService.proto

package org.example.proto;

public final class NetworkTopologyServiceOuterClass {
  private NetworkTopologyServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_example_proto_AddNodeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_example_proto_AddNodeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_example_proto_DeleteNodeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_example_proto_DeleteNodeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_example_proto_Response_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_example_proto_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034NetworkTopologyService.proto\022\021org.exam" +
      "ple.proto\"6\n\016AddNodeRequest\022\n\n\002id\030\001 \001(\t\022" +
      "\n\n\002ip\030\002 \001(\t\022\014\n\004port\030\003 \001(\005\"\037\n\021DeleteNodeR" +
      "equest\022\n\n\002id\030\001 \001(\t\"\030\n\010Response\022\014\n\004code\030\001" +
      " \001(\0052\264\001\n\026NetworkTopologyService\022I\n\007addNo" +
      "de\022!.org.example.proto.AddNodeRequest\032\033." +
      "org.example.proto.Response\022O\n\ndeleteNode" +
      "\022$.org.example.proto.DeleteNodeRequest\032\033" +
      ".org.example.proto.ResponseB\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_org_example_proto_AddNodeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_example_proto_AddNodeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_example_proto_AddNodeRequest_descriptor,
        new java.lang.String[] { "Id", "Ip", "Port", });
    internal_static_org_example_proto_DeleteNodeRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_org_example_proto_DeleteNodeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_example_proto_DeleteNodeRequest_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_org_example_proto_Response_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_org_example_proto_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_example_proto_Response_descriptor,
        new java.lang.String[] { "Code", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
