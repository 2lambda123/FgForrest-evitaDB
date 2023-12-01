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
// source: GrpcPriceMutations.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation}
 */
public final class GrpcRemovePriceMutation extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation)
    GrpcRemovePriceMutationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcRemovePriceMutation.newBuilder() to construct.
  private GrpcRemovePriceMutation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcRemovePriceMutation() {
    priceList_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcRemovePriceMutation();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcRemovePriceMutation(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            priceId_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            priceList_ = s;
            break;
          }
          case 26: {
            io.evitadb.externalApi.grpc.generated.GrpcCurrency.Builder subBuilder = null;
            if (currency_ != null) {
              subBuilder = currency_.toBuilder();
            }
            currency_ = input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcCurrency.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(currency_);
              currency_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.evitadb.externalApi.grpc.generated.GrpcPriceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcRemovePriceMutation_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcPriceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcRemovePriceMutation_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation.class, io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation.Builder.class);
  }

  public static final int PRICEID_FIELD_NUMBER = 1;
  private int priceId_;
  /**
   * <pre>
   * Contains identification of the price in the external systems. This id is expected to be used for the synchronization
   * of the price in relation with the primary source of the prices.
   * This id is used to uniquely find a price within same price list and currency and is mandatory.
   * </pre>
   *
   * <code>int32 priceId = 1;</code>
   * @return The priceId.
   */
  @java.lang.Override
  public int getPriceId() {
    return priceId_;
  }

  public static final int PRICELIST_FIELD_NUMBER = 2;
  private volatile java.lang.Object priceList_;
  /**
   * <pre>
   * Contains identification of the price list in the external system. Each price must reference a price list. Price list
   * identification may refer to another Evita entity or may contain any external price list identification
   * (for example id or unique name of the price list in the external system).
   * Single entity is expected to have single price for the price list unless there is `validity` specified.
   * In other words there is no sense to have multiple concurrently valid prices for the same entity that have roots
   * in the same price list.
   * </pre>
   *
   * <code>string priceList = 2;</code>
   * @return The priceList.
   */
  @java.lang.Override
  public java.lang.String getPriceList() {
    java.lang.Object ref = priceList_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      priceList_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Contains identification of the price list in the external system. Each price must reference a price list. Price list
   * identification may refer to another Evita entity or may contain any external price list identification
   * (for example id or unique name of the price list in the external system).
   * Single entity is expected to have single price for the price list unless there is `validity` specified.
   * In other words there is no sense to have multiple concurrently valid prices for the same entity that have roots
   * in the same price list.
   * </pre>
   *
   * <code>string priceList = 2;</code>
   * @return The bytes for priceList.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPriceListBytes() {
    java.lang.Object ref = priceList_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      priceList_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CURRENCY_FIELD_NUMBER = 3;
  private io.evitadb.externalApi.grpc.generated.GrpcCurrency currency_;
  /**
   * <pre>
   * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
   * @return Whether the currency field is set.
   */
  @java.lang.Override
  public boolean hasCurrency() {
    return currency_ != null;
  }
  /**
   * <pre>
   * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
   * @return The currency.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcCurrency getCurrency() {
    return currency_ == null ? io.evitadb.externalApi.grpc.generated.GrpcCurrency.getDefaultInstance() : currency_;
  }
  /**
   * <pre>
   * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcCurrencyOrBuilder getCurrencyOrBuilder() {
    return getCurrency();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (priceId_ != 0) {
      output.writeInt32(1, priceId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(priceList_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, priceList_);
    }
    if (currency_ != null) {
      output.writeMessage(3, getCurrency());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (priceId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, priceId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(priceList_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, priceList_);
    }
    if (currency_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getCurrency());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation other = (io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation) obj;

    if (getPriceId()
        != other.getPriceId()) return false;
    if (!getPriceList()
        .equals(other.getPriceList())) return false;
    if (hasCurrency() != other.hasCurrency()) return false;
    if (hasCurrency()) {
      if (!getCurrency()
          .equals(other.getCurrency())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PRICEID_FIELD_NUMBER;
    hash = (53 * hash) + getPriceId();
    hash = (37 * hash) + PRICELIST_FIELD_NUMBER;
    hash = (53 * hash) + getPriceList().hashCode();
    if (hasCurrency()) {
      hash = (37 * hash) + CURRENCY_FIELD_NUMBER;
      hash = (53 * hash) + getCurrency().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation)
      io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcPriceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcRemovePriceMutation_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcPriceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcRemovePriceMutation_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation.class, io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      priceId_ = 0;

      priceList_ = "";

      if (currencyBuilder_ == null) {
        currency_ = null;
      } else {
        currency_ = null;
        currencyBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcPriceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcRemovePriceMutation_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation build() {
      io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation result = new io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation(this);
      result.priceId_ = priceId_;
      result.priceList_ = priceList_;
      if (currencyBuilder_ == null) {
        result.currency_ = currency_;
      } else {
        result.currency_ = currencyBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation.getDefaultInstance()) return this;
      if (other.getPriceId() != 0) {
        setPriceId(other.getPriceId());
      }
      if (!other.getPriceList().isEmpty()) {
        priceList_ = other.priceList_;
        onChanged();
      }
      if (other.hasCurrency()) {
        mergeCurrency(other.getCurrency());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int priceId_ ;
    /**
     * <pre>
     * Contains identification of the price in the external systems. This id is expected to be used for the synchronization
     * of the price in relation with the primary source of the prices.
     * This id is used to uniquely find a price within same price list and currency and is mandatory.
     * </pre>
     *
     * <code>int32 priceId = 1;</code>
     * @return The priceId.
     */
    @java.lang.Override
    public int getPriceId() {
      return priceId_;
    }
    /**
     * <pre>
     * Contains identification of the price in the external systems. This id is expected to be used for the synchronization
     * of the price in relation with the primary source of the prices.
     * This id is used to uniquely find a price within same price list and currency and is mandatory.
     * </pre>
     *
     * <code>int32 priceId = 1;</code>
     * @param value The priceId to set.
     * @return This builder for chaining.
     */
    public Builder setPriceId(int value) {

      priceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Contains identification of the price in the external systems. This id is expected to be used for the synchronization
     * of the price in relation with the primary source of the prices.
     * This id is used to uniquely find a price within same price list and currency and is mandatory.
     * </pre>
     *
     * <code>int32 priceId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPriceId() {

      priceId_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object priceList_ = "";
    /**
     * <pre>
     * Contains identification of the price list in the external system. Each price must reference a price list. Price list
     * identification may refer to another Evita entity or may contain any external price list identification
     * (for example id or unique name of the price list in the external system).
     * Single entity is expected to have single price for the price list unless there is `validity` specified.
     * In other words there is no sense to have multiple concurrently valid prices for the same entity that have roots
     * in the same price list.
     * </pre>
     *
     * <code>string priceList = 2;</code>
     * @return The priceList.
     */
    public java.lang.String getPriceList() {
      java.lang.Object ref = priceList_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        priceList_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Contains identification of the price list in the external system. Each price must reference a price list. Price list
     * identification may refer to another Evita entity or may contain any external price list identification
     * (for example id or unique name of the price list in the external system).
     * Single entity is expected to have single price for the price list unless there is `validity` specified.
     * In other words there is no sense to have multiple concurrently valid prices for the same entity that have roots
     * in the same price list.
     * </pre>
     *
     * <code>string priceList = 2;</code>
     * @return The bytes for priceList.
     */
    public com.google.protobuf.ByteString
        getPriceListBytes() {
      java.lang.Object ref = priceList_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        priceList_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Contains identification of the price list in the external system. Each price must reference a price list. Price list
     * identification may refer to another Evita entity or may contain any external price list identification
     * (for example id or unique name of the price list in the external system).
     * Single entity is expected to have single price for the price list unless there is `validity` specified.
     * In other words there is no sense to have multiple concurrently valid prices for the same entity that have roots
     * in the same price list.
     * </pre>
     *
     * <code>string priceList = 2;</code>
     * @param value The priceList to set.
     * @return This builder for chaining.
     */
    public Builder setPriceList(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      priceList_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Contains identification of the price list in the external system. Each price must reference a price list. Price list
     * identification may refer to another Evita entity or may contain any external price list identification
     * (for example id or unique name of the price list in the external system).
     * Single entity is expected to have single price for the price list unless there is `validity` specified.
     * In other words there is no sense to have multiple concurrently valid prices for the same entity that have roots
     * in the same price list.
     * </pre>
     *
     * <code>string priceList = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPriceList() {

      priceList_ = getDefaultInstance().getPriceList();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Contains identification of the price list in the external system. Each price must reference a price list. Price list
     * identification may refer to another Evita entity or may contain any external price list identification
     * (for example id or unique name of the price list in the external system).
     * Single entity is expected to have single price for the price list unless there is `validity` specified.
     * In other words there is no sense to have multiple concurrently valid prices for the same entity that have roots
     * in the same price list.
     * </pre>
     *
     * <code>string priceList = 2;</code>
     * @param value The bytes for priceList to set.
     * @return This builder for chaining.
     */
    public Builder setPriceListBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      priceList_ = value;
      onChanged();
      return this;
    }

    private io.evitadb.externalApi.grpc.generated.GrpcCurrency currency_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcCurrency, io.evitadb.externalApi.grpc.generated.GrpcCurrency.Builder, io.evitadb.externalApi.grpc.generated.GrpcCurrencyOrBuilder> currencyBuilder_;
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     * @return Whether the currency field is set.
     */
    public boolean hasCurrency() {
      return currencyBuilder_ != null || currency_ != null;
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     * @return The currency.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcCurrency getCurrency() {
      if (currencyBuilder_ == null) {
        return currency_ == null ? io.evitadb.externalApi.grpc.generated.GrpcCurrency.getDefaultInstance() : currency_;
      } else {
        return currencyBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     */
    public Builder setCurrency(io.evitadb.externalApi.grpc.generated.GrpcCurrency value) {
      if (currencyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        currency_ = value;
        onChanged();
      } else {
        currencyBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     */
    public Builder setCurrency(
        io.evitadb.externalApi.grpc.generated.GrpcCurrency.Builder builderForValue) {
      if (currencyBuilder_ == null) {
        currency_ = builderForValue.build();
        onChanged();
      } else {
        currencyBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     */
    public Builder mergeCurrency(io.evitadb.externalApi.grpc.generated.GrpcCurrency value) {
      if (currencyBuilder_ == null) {
        if (currency_ != null) {
          currency_ =
            io.evitadb.externalApi.grpc.generated.GrpcCurrency.newBuilder(currency_).mergeFrom(value).buildPartial();
        } else {
          currency_ = value;
        }
        onChanged();
      } else {
        currencyBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     */
    public Builder clearCurrency() {
      if (currencyBuilder_ == null) {
        currency_ = null;
        onChanged();
      } else {
        currency_ = null;
        currencyBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcCurrency.Builder getCurrencyBuilder() {

      onChanged();
      return getCurrencyFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcCurrencyOrBuilder getCurrencyOrBuilder() {
      if (currencyBuilder_ != null) {
        return currencyBuilder_.getMessageOrBuilder();
      } else {
        return currency_ == null ?
            io.evitadb.externalApi.grpc.generated.GrpcCurrency.getDefaultInstance() : currency_;
      }
    }
    /**
     * <pre>
     * Identification of the currency. Three-letter form according to [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217).
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcCurrency currency = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcCurrency, io.evitadb.externalApi.grpc.generated.GrpcCurrency.Builder, io.evitadb.externalApi.grpc.generated.GrpcCurrencyOrBuilder>
        getCurrencyFieldBuilder() {
      if (currencyBuilder_ == null) {
        currencyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcCurrency, io.evitadb.externalApi.grpc.generated.GrpcCurrency.Builder, io.evitadb.externalApi.grpc.generated.GrpcCurrencyOrBuilder>(
                getCurrency(),
                getParentForChildren(),
                isClean());
        currency_ = null;
      }
      return currencyBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation)
  private static final io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcRemovePriceMutation>
      PARSER = new com.google.protobuf.AbstractParser<GrpcRemovePriceMutation>() {
    @java.lang.Override
    public GrpcRemovePriceMutation parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcRemovePriceMutation(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcRemovePriceMutation> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcRemovePriceMutation> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcRemovePriceMutation getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

