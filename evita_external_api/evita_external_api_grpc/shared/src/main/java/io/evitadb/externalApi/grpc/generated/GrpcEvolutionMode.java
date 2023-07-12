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
 * Protobuf enum {@code io.evitadb.externalApi.grpc.generated.GrpcEvolutionMode}
 */
public enum GrpcEvolutionMode
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>ADAPT_PRIMARY_KEY_GENERATION = 0;</code>
   */
  ADAPT_PRIMARY_KEY_GENERATION(0),
  /**
   * <code>ADDING_ATTRIBUTES = 1;</code>
   */
  ADDING_ATTRIBUTES(1),
  /**
   * <code>ADDING_ASSOCIATED_DATA = 2;</code>
   */
  ADDING_ASSOCIATED_DATA(2),
  /**
   * <code>ADDING_REFERENCES = 3;</code>
   */
  ADDING_REFERENCES(3),
  /**
   * <code>ADDING_PRICES = 4;</code>
   */
  ADDING_PRICES(4),
  /**
   * <code>ADDING_LOCALES = 5;</code>
   */
  ADDING_LOCALES(5),
  /**
   * <code>ADDING_CURRENCIES = 6;</code>
   */
  ADDING_CURRENCIES(6),
  /**
   * <code>ADDING_HIERARCHY = 7;</code>
   */
  ADDING_HIERARCHY(7),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>ADAPT_PRIMARY_KEY_GENERATION = 0;</code>
   */
  public static final int ADAPT_PRIMARY_KEY_GENERATION_VALUE = 0;
  /**
   * <code>ADDING_ATTRIBUTES = 1;</code>
   */
  public static final int ADDING_ATTRIBUTES_VALUE = 1;
  /**
   * <code>ADDING_ASSOCIATED_DATA = 2;</code>
   */
  public static final int ADDING_ASSOCIATED_DATA_VALUE = 2;
  /**
   * <code>ADDING_REFERENCES = 3;</code>
   */
  public static final int ADDING_REFERENCES_VALUE = 3;
  /**
   * <code>ADDING_PRICES = 4;</code>
   */
  public static final int ADDING_PRICES_VALUE = 4;
  /**
   * <code>ADDING_LOCALES = 5;</code>
   */
  public static final int ADDING_LOCALES_VALUE = 5;
  /**
   * <code>ADDING_CURRENCIES = 6;</code>
   */
  public static final int ADDING_CURRENCIES_VALUE = 6;
  /**
   * <code>ADDING_HIERARCHY = 7;</code>
   */
  public static final int ADDING_HIERARCHY_VALUE = 7;


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
  public static GrpcEvolutionMode valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static GrpcEvolutionMode forNumber(int value) {
    switch (value) {
      case 0: return ADAPT_PRIMARY_KEY_GENERATION;
      case 1: return ADDING_ATTRIBUTES;
      case 2: return ADDING_ASSOCIATED_DATA;
      case 3: return ADDING_REFERENCES;
      case 4: return ADDING_PRICES;
      case 5: return ADDING_LOCALES;
      case 6: return ADDING_CURRENCIES;
      case 7: return ADDING_HIERARCHY;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<GrpcEvolutionMode>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      GrpcEvolutionMode> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<GrpcEvolutionMode>() {
          public GrpcEvolutionMode findValueByNumber(int number) {
            return GrpcEvolutionMode.forNumber(number);
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

  private static final GrpcEvolutionMode[] VALUES = values();

  public static GrpcEvolutionMode valueOf(
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

  private GrpcEvolutionMode(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:io.evitadb.externalApi.grpc.generated.GrpcEvolutionMode)
}

