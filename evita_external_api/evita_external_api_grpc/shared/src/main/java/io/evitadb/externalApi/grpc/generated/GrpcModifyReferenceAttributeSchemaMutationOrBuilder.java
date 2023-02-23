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
// source: GrpcReferenceSchemaMutations.proto

package io.evitadb.externalApi.grpc.generated;

public interface GrpcModifyReferenceAttributeSchemaMutationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.evitadb.externalApi.grpc.generated.GrpcModifyReferenceAttributeSchemaMutation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaMutation attributeSchemaMutation = 2;</code>
   * @return Whether the attributeSchemaMutation field is set.
   */
  boolean hasAttributeSchemaMutation();
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaMutation attributeSchemaMutation = 2;</code>
   * @return The attributeSchemaMutation.
   */
  io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaMutation getAttributeSchemaMutation();
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaMutation attributeSchemaMutation = 2;</code>
   */
  io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaMutationOrBuilder getAttributeSchemaMutationOrBuilder();
}
