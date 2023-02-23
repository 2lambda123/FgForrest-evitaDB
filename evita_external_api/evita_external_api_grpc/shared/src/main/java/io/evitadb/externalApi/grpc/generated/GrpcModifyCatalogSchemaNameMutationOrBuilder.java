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
// source: GrpcCatalogSchemaMutations.proto

package io.evitadb.externalApi.grpc.generated;

public interface GrpcModifyCatalogSchemaNameMutationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.evitadb.externalApi.grpc.generated.GrpcModifyCatalogSchemaNameMutation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string catalogName = 1;</code>
   * @return The catalogName.
   */
  java.lang.String getCatalogName();
  /**
   * <code>string catalogName = 1;</code>
   * @return The bytes for catalogName.
   */
  com.google.protobuf.ByteString
      getCatalogNameBytes();

  /**
   * <code>string newCatalogName = 2;</code>
   * @return The newCatalogName.
   */
  java.lang.String getNewCatalogName();
  /**
   * <code>string newCatalogName = 2;</code>
   * @return The bytes for newCatalogName.
   */
  com.google.protobuf.ByteString
      getNewCatalogNameBytes();

  /**
   * <code>bool overwriteTarget = 3;</code>
   * @return The overwriteTarget.
   */
  boolean getOverwriteTarget();
}
