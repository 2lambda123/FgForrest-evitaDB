// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GrpcChangeCatalogCapture.proto

package io.evitadb.externalApi.grpc.generated;

public final class GrpcChangeDataCaptureOuterClass {
  private GrpcChangeDataCaptureOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeSystemCapture_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeSystemCapture_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeDataCapture_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeDataCapture_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcCaptureSince_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_evitadb_externalApi_grpc_generated_GrpcCaptureSince_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcSchemaSite_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_evitadb_externalApi_grpc_generated_GrpcSchemaSite_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcDataSite_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_evitadb_externalApi_grpc_generated_GrpcDataSite_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033GrpcChangeDataCapture.proto\022%io.evitad" +
      "b.externalApi.grpc.generated\032\017GrpcEnums." +
      "proto\032\037GrpcCatalogSchemaMutation.proto\032\027" +
      "GrpcLocalMutation.proto\032\030GrpcEntityMutat" +
      "ion.proto\032\036GrpcEntitySchemaMutation.prot" +
      "o\032\036google/protobuf/wrappers.proto\"\317\001\n\027Gr" +
      "pcChangeSystemCapture\022\017\n\007catalog\030\001 \001(\t\022G" +
      "\n\toperation\030\002 \001(\01624.io.evitadb.externalA" +
      "pi.grpc.generated.GrpcOperation\022Z\n\010mutat" +
      "ion\030\003 \001(\0132H.io.evitadb.externalApi.grpc." +
      "generated.GrpcTopLevelCatalogSchemaMutat" +
      "ion\"\362\004\n\025GrpcChangeDataCapture\022D\n\004area\030\001 " +
      "\001(\01626.io.evitadb.externalApi.grpc.genera" +
      "ted.GrpcCaptureArea\022\017\n\007catalog\030\002 \001(\t\022\022\n\n" +
      "entityType\030\003 \001(\t\022,\n\007version\030\004 \001(\0132\033.goog" +
      "le.protobuf.Int32Value\022G\n\toperation\030\005 \001(" +
      "\01624.io.evitadb.externalApi.grpc.generate" +
      "d.GrpcOperation\022S\n\016entityMutation\030\006 \001(\0132" +
      "9.io.evitadb.externalApi.grpc.generated." +
      "GrpcEntityMutationH\000\022Q\n\rlocalMutation\030\007 " +
      "\001(\01328.io.evitadb.externalApi.grpc.genera" +
      "ted.GrpcLocalMutationH\000\022_\n\024entitySchemaM" +
      "utation\030\010 \001(\0132?.io.evitadb.externalApi.g" +
      "rpc.generated.GrpcEntitySchemaMutationH\000" +
      "\022f\n\025catalogSchemaMutation\030\t \001(\0132E.io.evi" +
      "tadb.externalApi.grpc.generated.GrpcLoca" +
      "lCatalogSchemaMutationH\000B\006\n\004body\"W\n\020Grpc" +
      "CaptureSince\022,\n\007version\030\001 \001(\0132\033.google.p" +
      "rotobuf.Int32Value\022\025\n\rtransactionId\030\002 \001(" +
      "\003\"n\n\016GrpcSchemaSite\022\022\n\nentityType\030\001 \001(\t\022" +
      "H\n\noperations\030\002 \003(\01624.io.evitadb.externa" +
      "lApi.grpc.generated.GrpcOperation\"\367\001\n\014Gr" +
      "pcDataSite\022\022\n\nentityType\030\001 \001(\t\0225\n\020entity" +
      "PrimaryKey\030\002 \001(\0132\033.google.protobuf.Int32" +
      "Value\022R\n\017classifierTypes\030\003 \003(\01629.io.evit" +
      "adb.externalApi.grpc.generated.GrpcClass" +
      "ifierType\022H\n\noperations\030\004 \003(\01624.io.evita" +
      "db.externalApi.grpc.generated.GrpcOperat" +
      "ionB\014P\001\252\002\007EvitaDBb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.evitadb.externalApi.grpc.generated.GrpcEnums.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcCatalogSchemaMutation.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcLocalMutationOuterClass.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOuterClass.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcEntitySchemaMutationOuterClass.getDescriptor(),
          com.google.protobuf.WrappersProto.getDescriptor(),
        });
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeSystemCapture_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeSystemCapture_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeSystemCapture_descriptor,
        new java.lang.String[] { "Catalog", "Operation", "Mutation", });
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeDataCapture_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeDataCapture_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_evitadb_externalApi_grpc_generated_GrpcChangeDataCapture_descriptor,
        new java.lang.String[] { "Area", "Catalog", "EntityType", "Version", "Operation", "EntityMutation", "LocalMutation", "EntitySchemaMutation", "CatalogSchemaMutation", "Body", });
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcCaptureSince_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcCaptureSince_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_evitadb_externalApi_grpc_generated_GrpcCaptureSince_descriptor,
        new java.lang.String[] { "Version", "TransactionId", });
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcSchemaSite_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcSchemaSite_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_evitadb_externalApi_grpc_generated_GrpcSchemaSite_descriptor,
        new java.lang.String[] { "EntityType", "Operations", });
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcDataSite_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcDataSite_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_evitadb_externalApi_grpc_generated_GrpcDataSite_descriptor,
        new java.lang.String[] { "EntityType", "EntityPrimaryKey", "ClassifierTypes", "Operations", });
    io.evitadb.externalApi.grpc.generated.GrpcEnums.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcCatalogSchemaMutation.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcLocalMutationOuterClass.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOuterClass.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcEntitySchemaMutationOuterClass.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
