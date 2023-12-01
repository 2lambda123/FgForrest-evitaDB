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
// source: GrpcEntityMutation.proto

package io.evitadb.externalApi.grpc.generated;

public interface GrpcEntityUpsertMutationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The type of the entity to be mutated.
   * </pre>
   *
   * <code>string entityType = 1;</code>
   * @return The entityType.
   */
  java.lang.String getEntityType();
  /**
   * <pre>
   * The type of the entity to be mutated.
   * </pre>
   *
   * <code>string entityType = 1;</code>
   * @return The bytes for entityType.
   */
  com.google.protobuf.ByteString
      getEntityTypeBytes();

  /**
   * <pre>
   * The primary key of the entity to be mutated.
   * </pre>
   *
   * <code>.google.protobuf.Int32Value entityPrimaryKey = 2;</code>
   * @return Whether the entityPrimaryKey field is set.
   */
  boolean hasEntityPrimaryKey();
  /**
   * <pre>
   * The primary key of the entity to be mutated.
   * </pre>
   *
   * <code>.google.protobuf.Int32Value entityPrimaryKey = 2;</code>
   * @return The entityPrimaryKey.
   */
  com.google.protobuf.Int32Value getEntityPrimaryKey();
  /**
   * <pre>
   * The primary key of the entity to be mutated.
   * </pre>
   *
   * <code>.google.protobuf.Int32Value entityPrimaryKey = 2;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getEntityPrimaryKeyOrBuilder();

  /**
   * <pre>
   * Controls behaviour of the upsert operation.
   * - MUST_NOT_EXIST: use when you know you'll be inserting a new value
   * - MUST_EXIST: use when you know you'll be updating an existing value
   * - MAY_EXIST: use when you're not sure
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityExistence entityExistence = 3;</code>
   * @return The enum numeric value on the wire for entityExistence.
   */
  int getEntityExistenceValue();
  /**
   * <pre>
   * Controls behaviour of the upsert operation.
   * - MUST_NOT_EXIST: use when you know you'll be inserting a new value
   * - MUST_EXIST: use when you know you'll be updating an existing value
   * - MAY_EXIST: use when you're not sure
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityExistence entityExistence = 3;</code>
   * @return The entityExistence.
   */
  io.evitadb.externalApi.grpc.generated.GrpcEntityExistence getEntityExistence();

  /**
   * <pre>
   * List of all local mutations that modify internal data of the entity.
   * </pre>
   *
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcLocalMutation mutations = 4;</code>
   */
  java.util.List<io.evitadb.externalApi.grpc.generated.GrpcLocalMutation>
      getMutationsList();
  /**
   * <pre>
   * List of all local mutations that modify internal data of the entity.
   * </pre>
   *
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcLocalMutation mutations = 4;</code>
   */
  io.evitadb.externalApi.grpc.generated.GrpcLocalMutation getMutations(int index);
  /**
   * <pre>
   * List of all local mutations that modify internal data of the entity.
   * </pre>
   *
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcLocalMutation mutations = 4;</code>
   */
  int getMutationsCount();
  /**
   * <pre>
   * List of all local mutations that modify internal data of the entity.
   * </pre>
   *
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcLocalMutation mutations = 4;</code>
   */
  java.util.List<? extends io.evitadb.externalApi.grpc.generated.GrpcLocalMutationOrBuilder>
      getMutationsOrBuilderList();
  /**
   * <pre>
   * List of all local mutations that modify internal data of the entity.
   * </pre>
   *
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcLocalMutation mutations = 4;</code>
   */
  io.evitadb.externalApi.grpc.generated.GrpcLocalMutationOrBuilder getMutationsOrBuilder(
      int index);
}
