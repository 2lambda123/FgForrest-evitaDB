/*
 *
 *                         _ _        ____  ____
 *               _____   _(_) |_ __ _|  _ \| __ )
 *              / _ \ \ / / | __/ _` | | | |  _ \
 *             |  __/\ V /| | || (_| | |_| | |_) |
 *              \___| \_/ |_|\__\__,_|____/|____/
 *
 *   Copyright (c) 2023
 *
 *   Licensed under the Business Source License, Version 1.1 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://github.com/FgForrest/evitaDB/blob/main/LICENSE
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GrpcLocalMutation.proto

package io.evitadb.externalApi.grpc.generated;

public final class GrpcLocalMutationOuterClass {
  private GrpcLocalMutationOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalMutation_descriptor;
  static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalMutation_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027GrpcLocalMutation.proto\022%io.evitadb.ex" +
      "ternalApi.grpc.generated\032\034GrpcAttributeM" +
      "utations.proto\032!GrpcAssociatedDataMutati" +
      "ons.proto\032\030GrpcPriceMutations.proto\032\031Grp" +
      "cEntityMutations.proto\032\034GrpcReferenceMut" +
      "ations.proto\"\316\014\n\021GrpcLocalMutation\022m\n\033ap" +
      "plyDeltaAttributeMutation\030\001 \001(\0132F.io.evi" +
      "tadb.externalApi.grpc.generated.GrpcAppl" +
      "yDeltaAttributeMutationH\000\022e\n\027upsertAttri" +
      "buteMutation\030\002 \001(\0132B.io.evitadb.external" +
      "Api.grpc.generated.GrpcUpsertAttributeMu" +
      "tationH\000\022e\n\027removeAttributeMutation\030\003 \001(" +
      "\0132B.io.evitadb.externalApi.grpc.generate" +
      "d.GrpcRemoveAttributeMutationH\000\022o\n\034upser" +
      "tAssociatedDataMutation\030\004 \001(\0132G.io.evita" +
      "db.externalApi.grpc.generated.GrpcUpsert" +
      "AssociatedDataMutationH\000\022o\n\034removeAssoci" +
      "atedDataMutation\030\005 \001(\0132G.io.evitadb.exte" +
      "rnalApi.grpc.generated.GrpcRemoveAssocia" +
      "tedDataMutationH\000\022]\n\023upsertPriceMutation" +
      "\030\006 \001(\0132>.io.evitadb.externalApi.grpc.gen" +
      "erated.GrpcUpsertPriceMutationH\000\022]\n\023remo" +
      "vePriceMutation\030\007 \001(\0132>.io.evitadb.exter" +
      "nalApi.grpc.generated.GrpcRemovePriceMut" +
      "ationH\000\022}\n#setPriceInnerRecordHandlingMu" +
      "tation\030\010 \001(\0132N.io.evitadb.externalApi.gr" +
      "pc.generated.GrpcSetPriceInnerRecordHand" +
      "lingMutationH\000\022Y\n\021setParentMutation\030\t \001(" +
      "\0132<.io.evitadb.externalApi.grpc.generate" +
      "d.GrpcSetParentMutationH\000\022_\n\024removeParen" +
      "tMutation\030\n \001(\0132?.io.evitadb.externalApi" +
      ".grpc.generated.GrpcRemoveParentMutation" +
      "H\000\022e\n\027insertReferenceMutation\030\013 \001(\0132B.io" +
      ".evitadb.externalApi.grpc.generated.Grpc" +
      "InsertReferenceMutationH\000\022e\n\027removeRefer" +
      "enceMutation\030\014 \001(\0132B.io.evitadb.external" +
      "Api.grpc.generated.GrpcRemoveReferenceMu" +
      "tationH\000\022i\n\031setReferenceGroupMutation\030\r " +
      "\001(\0132D.io.evitadb.externalApi.grpc.genera" +
      "ted.GrpcSetReferenceGroupMutationH\000\022o\n\034r" +
      "emoveReferenceGroupMutation\030\016 \001(\0132G.io.e" +
      "vitadb.externalApi.grpc.generated.GrpcRe" +
      "moveReferenceGroupMutationH\000\022k\n\032referenc" +
      "eAttributeMutation\030\017 \001(\0132E.io.evitadb.ex" +
      "ternalApi.grpc.generated.GrpcReferenceAt" +
      "tributeMutationH\000B\n\n\010mutationB\002P\001b\006proto" +
      "3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.evitadb.externalApi.grpc.generated.GrpcAttributeMutations.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataMutations.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcPriceMutations.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcEntityMutations.getDescriptor(),
          io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.getDescriptor(),
        });
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalMutation_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalMutation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalMutation_descriptor,
        new java.lang.String[] { "ApplyDeltaAttributeMutation", "UpsertAttributeMutation", "RemoveAttributeMutation", "UpsertAssociatedDataMutation", "RemoveAssociatedDataMutation", "UpsertPriceMutation", "RemovePriceMutation", "SetPriceInnerRecordHandlingMutation", "SetParentMutation", "RemoveParentMutation", "InsertReferenceMutation", "RemoveReferenceMutation", "SetReferenceGroupMutation", "RemoveReferenceGroupMutation", "ReferenceAttributeMutation", "Mutation", });
    io.evitadb.externalApi.grpc.generated.GrpcAttributeMutations.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataMutations.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcPriceMutations.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcEntityMutations.getDescriptor();
    io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
