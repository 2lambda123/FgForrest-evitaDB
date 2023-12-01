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

public interface GrpcModifyReferenceSchemaCardinalityMutationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.evitadb.externalApi.grpc.generated.GrpcModifyReferenceSchemaCardinalityMutation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Name of the reference the mutation is targeting.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * Name of the reference the mutation is targeting.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * Cardinality describes the expected count of relations of this type. In evitaDB we define only one-way
   * relationship from the perspective of the entity. We stick to the ERD modelling
   * [standards](https://www.gleek.io/blog/crows-foot-notation.html) here. Cardinality affect the design
   * of the client API (returning only single reference or collections) and also help us to protect the consistency
   * of the data so that conforms to the creator mental model.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcCardinality cardinality = 2;</code>
   * @return The enum numeric value on the wire for cardinality.
   */
  int getCardinalityValue();
  /**
   * <pre>
   * Cardinality describes the expected count of relations of this type. In evitaDB we define only one-way
   * relationship from the perspective of the entity. We stick to the ERD modelling
   * [standards](https://www.gleek.io/blog/crows-foot-notation.html) here. Cardinality affect the design
   * of the client API (returning only single reference or collections) and also help us to protect the consistency
   * of the data so that conforms to the creator mental model.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcCardinality cardinality = 2;</code>
   * @return The cardinality.
   */
  io.evitadb.externalApi.grpc.generated.GrpcCardinality getCardinality();
}
