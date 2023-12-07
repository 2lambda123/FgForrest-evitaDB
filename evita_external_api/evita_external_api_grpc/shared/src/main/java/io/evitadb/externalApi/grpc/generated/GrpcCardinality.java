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
// source: GrpcEnums.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * <pre>
 * In EvitaDB we define only one-way relationship from the perspective of the entity. We stick to the ERD modelling
 * &lt;a href="https://www.gleek.io/blog/crows-foot-notation.html"&gt;standards&lt;/a&gt; here.
 * </pre>
 *
 * Protobuf enum {@code io.evitadb.externalApi.grpc.generated.GrpcCardinality}
 */
public enum GrpcCardinality
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * No cardinality specified.
   * </pre>
   *
   * <code>NOT_SPECIFIED = 0;</code>
   */
  NOT_SPECIFIED(0),
  /**
   * <pre>
   * Relation may be missing completely, but if it exists - there is never more than single relation of this type.
   * </pre>
   *
   * <code>ZERO_OR_ONE = 1;</code>
   */
  ZERO_OR_ONE(1),
  /**
   * <pre>
   * There is always single relation of this type.
   * </pre>
   *
   * <code>EXACTLY_ONE = 2;</code>
   */
  EXACTLY_ONE(2),
  /**
   * <pre>
   * Relation may be missing completely, but there may be also one or more relations of this type.
   * </pre>
   *
   * <code>ZERO_OR_MORE = 3;</code>
   */
  ZERO_OR_MORE(3),
  /**
   * <pre>
   * There is always at least one relation of this type, but there may be also more than one.
   * </pre>
   *
   * <code>ONE_OR_MORE = 4;</code>
   */
  ONE_OR_MORE(4),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * No cardinality specified.
   * </pre>
   *
   * <code>NOT_SPECIFIED = 0;</code>
   */
  public static final int NOT_SPECIFIED_VALUE = 0;
  /**
   * <pre>
   * Relation may be missing completely, but if it exists - there is never more than single relation of this type.
   * </pre>
   *
   * <code>ZERO_OR_ONE = 1;</code>
   */
  public static final int ZERO_OR_ONE_VALUE = 1;
  /**
   * <pre>
   * There is always single relation of this type.
   * </pre>
   *
   * <code>EXACTLY_ONE = 2;</code>
   */
  public static final int EXACTLY_ONE_VALUE = 2;
  /**
   * <pre>
   * Relation may be missing completely, but there may be also one or more relations of this type.
   * </pre>
   *
   * <code>ZERO_OR_MORE = 3;</code>
   */
  public static final int ZERO_OR_MORE_VALUE = 3;
  /**
   * <pre>
   * There is always at least one relation of this type, but there may be also more than one.
   * </pre>
   *
   * <code>ONE_OR_MORE = 4;</code>
   */
  public static final int ONE_OR_MORE_VALUE = 4;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static GrpcCardinality valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static GrpcCardinality forNumber(int value) {
    switch (value) {
      case 0: return NOT_SPECIFIED;
      case 1: return ZERO_OR_ONE;
      case 2: return EXACTLY_ONE;
      case 3: return ZERO_OR_MORE;
      case 4: return ONE_OR_MORE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<GrpcCardinality>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      GrpcCardinality> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<GrpcCardinality>() {
          public GrpcCardinality findValueByNumber(int number) {
            return GrpcCardinality.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return io.evitadb.externalApi.grpc.generated.GrpcEnums.getDescriptor().getEnumTypes().get(14);
  }

  private static final GrpcCardinality[] VALUES = values();

  public static GrpcCardinality valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private GrpcCardinality(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:io.evitadb.externalApi.grpc.generated.GrpcCardinality)
}

