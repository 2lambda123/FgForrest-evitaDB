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
// source: GrpcEvita.proto

package io.evitadb.externalApi.grpc.generated;

public interface GrpcUpdateEvitaRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.evitadb.externalApi.grpc.generated.GrpcUpdateEvitaRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutation schemaMutations = 1;</code>
   */
  java.util.List<io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutation> 
      getSchemaMutationsList();
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutation schemaMutations = 1;</code>
   */
  io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutation getSchemaMutations(int index);
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutation schemaMutations = 1;</code>
   */
  int getSchemaMutationsCount();
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutation schemaMutations = 1;</code>
   */
  java.util.List<? extends io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutationOrBuilder> 
      getSchemaMutationsOrBuilderList();
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutation schemaMutations = 1;</code>
   */
  io.evitadb.externalApi.grpc.generated.GrpcTopLevelCatalogSchemaMutationOrBuilder getSchemaMutationsOrBuilder(
      int index);
}
