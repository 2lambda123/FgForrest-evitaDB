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
 * This enum is used to identify session type of the created session by gRPC server.
 * </pre>
 *
 * Protobuf enum {@code io.evitadb.externalApi.grpc.generated.GrpcSessionType}
 */
public enum GrpcSessionType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * Classic read-only session.
   * </pre>
   *
   * <code>READ_ONLY = 0;</code>
   */
  READ_ONLY(0),
  /**
   * <pre>
   * Classic read-write session.
   * </pre>
   *
   * <code>READ_WRITE = 1;</code>
   */
  READ_WRITE(1),
  /**
   * <pre>
   * Read only session that returns all fetched entities in a form of a `BinaryEntity`, which has all of its data represented in a binary form. Should be used only in combination with evitaDB's Java driver.
   * </pre>
   *
   * <code>BINARY_READ_ONLY = 2;</code>
   */
  BINARY_READ_ONLY(2),
  /**
   * <pre>
   * Read write session that returns all fetched entities in a form of a `BinaryEntity`, which has all of its data represented in a binary form. Should be used only in combination with evitaDB's Java driver.
   * </pre>
   *
   * <code>BINARY_READ_WRITE = 3;</code>
   */
  BINARY_READ_WRITE(3),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * Classic read-only session.
   * </pre>
   *
   * <code>READ_ONLY = 0;</code>
   */
  public static final int READ_ONLY_VALUE = 0;
  /**
   * <pre>
   * Classic read-write session.
   * </pre>
   *
   * <code>READ_WRITE = 1;</code>
   */
  public static final int READ_WRITE_VALUE = 1;
  /**
   * <pre>
   * Read only session that returns all fetched entities in a form of a `BinaryEntity`, which has all of its data represented in a binary form. Should be used only in combination with evitaDB's Java driver.
   * </pre>
   *
   * <code>BINARY_READ_ONLY = 2;</code>
   */
  public static final int BINARY_READ_ONLY_VALUE = 2;
  /**
   * <pre>
   * Read write session that returns all fetched entities in a form of a `BinaryEntity`, which has all of its data represented in a binary form. Should be used only in combination with evitaDB's Java driver.
   * </pre>
   *
   * <code>BINARY_READ_WRITE = 3;</code>
   */
  public static final int BINARY_READ_WRITE_VALUE = 3;


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
  public static GrpcSessionType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static GrpcSessionType forNumber(int value) {
    switch (value) {
      case 0: return READ_ONLY;
      case 1: return READ_WRITE;
      case 2: return BINARY_READ_ONLY;
      case 3: return BINARY_READ_WRITE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<GrpcSessionType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      GrpcSessionType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<GrpcSessionType>() {
          public GrpcSessionType findValueByNumber(int number) {
            return GrpcSessionType.forNumber(number);
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
    return io.evitadb.externalApi.grpc.generated.GrpcEnums.getDescriptor().getEnumTypes().get(13);
  }

  private static final GrpcSessionType[] VALUES = values();

  public static GrpcSessionType valueOf(
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

  private GrpcSessionType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:io.evitadb.externalApi.grpc.generated.GrpcSessionType)
}

