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
// source: GrpcEntityMutations.proto

package io.evitadb.externalApi.grpc.generated;

public interface GrpcSetHierarchicalPlacementMutationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.evitadb.externalApi.grpc.generated.GrpcSetHierarchicalPlacementMutation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.google.protobuf.Int32Value primaryKey = 1;</code>
   * @return Whether the primaryKey field is set.
   */
  boolean hasPrimaryKey();
  /**
   * <code>.google.protobuf.Int32Value primaryKey = 1;</code>
   * @return The primaryKey.
   */
  com.google.protobuf.Int32Value getPrimaryKey();
  /**
   * <code>.google.protobuf.Int32Value primaryKey = 1;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getPrimaryKeyOrBuilder();

  /**
   * <code>int32 orderAmongSiblings = 2;</code>
   * @return The orderAmongSiblings.
   */
  int getOrderAmongSiblings();
}
