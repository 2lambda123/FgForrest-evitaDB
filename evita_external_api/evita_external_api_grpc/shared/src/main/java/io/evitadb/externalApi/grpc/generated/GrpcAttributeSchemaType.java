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
 * Defines the type of the attribute schema
 * </pre>
 *
 * Protobuf enum {@code io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaType}
 */
public enum GrpcAttributeSchemaType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * attribute schema is GlobalAttributeSchemaContract
   * </pre>
   *
   * <code>GLOBAL_SCHEMA = 0;</code>
   */
  GLOBAL_SCHEMA(0),
  /**
   * <pre>
   * attribute schema is EntityAttributeSchemaContract
   * </pre>
   *
   * <code>ENTITY_SCHEMA = 1;</code>
   */
  ENTITY_SCHEMA(1),
  /**
   * <pre>
   * attribute schema is AttributeSchemaContract
   * </pre>
   *
   * <code>REFERENCE_SCHEMA = 2;</code>
   */
  REFERENCE_SCHEMA(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * attribute schema is GlobalAttributeSchemaContract
   * </pre>
   *
   * <code>GLOBAL_SCHEMA = 0;</code>
   */
  public static final int GLOBAL_SCHEMA_VALUE = 0;
  /**
   * <pre>
   * attribute schema is EntityAttributeSchemaContract
   * </pre>
   *
   * <code>ENTITY_SCHEMA = 1;</code>
   */
  public static final int ENTITY_SCHEMA_VALUE = 1;
  /**
   * <pre>
   * attribute schema is AttributeSchemaContract
   * </pre>
   *
   * <code>REFERENCE_SCHEMA = 2;</code>
   */
  public static final int REFERENCE_SCHEMA_VALUE = 2;


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
  public static GrpcAttributeSchemaType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static GrpcAttributeSchemaType forNumber(int value) {
    switch (value) {
      case 0: return GLOBAL_SCHEMA;
      case 1: return ENTITY_SCHEMA;
      case 2: return REFERENCE_SCHEMA;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<GrpcAttributeSchemaType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      GrpcAttributeSchemaType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<GrpcAttributeSchemaType>() {
          public GrpcAttributeSchemaType findValueByNumber(int number) {
            return GrpcAttributeSchemaType.forNumber(number);
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
    return io.evitadb.externalApi.grpc.generated.GrpcEnums.getDescriptor().getEnumTypes().get(15);
  }

  private static final GrpcAttributeSchemaType[] VALUES = values();

  public static GrpcAttributeSchemaType valueOf(
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

  private GrpcAttributeSchemaType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:io.evitadb.externalApi.grpc.generated.GrpcAttributeSchemaType)
}

