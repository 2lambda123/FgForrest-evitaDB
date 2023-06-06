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

/**
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation}
 */
public final class GrpcAllowEvolutionModeInCatalogSchemaMutation extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation)
    GrpcAllowEvolutionModeInCatalogSchemaMutationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcAllowEvolutionModeInCatalogSchemaMutation.newBuilder() to construct.
  private GrpcAllowEvolutionModeInCatalogSchemaMutation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcAllowEvolutionModeInCatalogSchemaMutation() {
    evolutionModes_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcAllowEvolutionModeInCatalogSchemaMutation();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcAllowEvolutionModeInCatalogSchemaMutation(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
            int rawValue = input.readEnum();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              evolutionModes_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000001;
            }
            evolutionModes_.add(rawValue);
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int oldLimit = input.pushLimit(length);
            while(input.getBytesUntilLimit() > 0) {
              int rawValue = input.readEnum();
              if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                evolutionModes_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000001;
              }
              evolutionModes_.add(rawValue);
            }
            input.popLimit(oldLimit);
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        evolutionModes_ = java.util.Collections.unmodifiableList(evolutionModes_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.evitadb.externalApi.grpc.generated.GrpcCatalogSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcAllowEvolutionModeInCatalogSchemaMutation_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcCatalogSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcAllowEvolutionModeInCatalogSchemaMutation_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation.class, io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation.Builder.class);
  }

  public static final int EVOLUTIONMODES_FIELD_NUMBER = 1;
  private java.util.List<java.lang.Integer> evolutionModes_;
  private static final com.google.protobuf.Internal.ListAdapter.Converter<
      java.lang.Integer, io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode> evolutionModes_converter_ =
          new com.google.protobuf.Internal.ListAdapter.Converter<
              java.lang.Integer, io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode>() {
            public io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode convert(java.lang.Integer from) {
              @SuppressWarnings("deprecation")
              io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode result = io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode.valueOf(from);
              return result == null ? io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode.UNRECOGNIZED : result;
            }
          };
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
   * @return A list containing the evolutionModes.
   */
  @java.lang.Override
  public java.util.List<io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode> getEvolutionModesList() {
    return new com.google.protobuf.Internal.ListAdapter<
        java.lang.Integer, io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode>(evolutionModes_, evolutionModes_converter_);
  }
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
   * @return The count of evolutionModes.
   */
  @java.lang.Override
  public int getEvolutionModesCount() {
    return evolutionModes_.size();
  }
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
   * @param index The index of the element to return.
   * @return The evolutionModes at the given index.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode getEvolutionModes(int index) {
    return evolutionModes_converter_.convert(evolutionModes_.get(index));
  }
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
   * @return A list containing the enum numeric values on the wire for evolutionModes.
   */
  @java.lang.Override
  public java.util.List<java.lang.Integer>
  getEvolutionModesValueList() {
    return evolutionModes_;
  }
  /**
   * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
   * @param index The index of the value to return.
   * @return The enum numeric value on the wire of evolutionModes at the given index.
   */
  @java.lang.Override
  public int getEvolutionModesValue(int index) {
    return evolutionModes_.get(index);
  }
  private int evolutionModesMemoizedSerializedSize;

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
    getSerializedSize();
    if (getEvolutionModesList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(evolutionModesMemoizedSerializedSize);
    }
    for (int i = 0; i < evolutionModes_.size(); i++) {
      output.writeEnumNoTag(evolutionModes_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < evolutionModes_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeEnumSizeNoTag(evolutionModes_.get(i));
      }
      size += dataSize;
      if (!getEvolutionModesList().isEmpty()) {  size += 1;
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32SizeNoTag(dataSize);
      }evolutionModesMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation other = (io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation) obj;

    if (!evolutionModes_.equals(other.evolutionModes_)) return false;
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
    if (getEvolutionModesCount() > 0) {
      hash = (37 * hash) + EVOLUTIONMODES_FIELD_NUMBER;
      hash = (53 * hash) + evolutionModes_.hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation prototype) {
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
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation)
      io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcCatalogSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcAllowEvolutionModeInCatalogSchemaMutation_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcCatalogSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcAllowEvolutionModeInCatalogSchemaMutation_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation.class, io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation.newBuilder()
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
      evolutionModes_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcCatalogSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcAllowEvolutionModeInCatalogSchemaMutation_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation build() {
      io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation result = new io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        evolutionModes_ = java.util.Collections.unmodifiableList(evolutionModes_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.evolutionModes_ = evolutionModes_;
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation.getDefaultInstance()) return this;
      if (!other.evolutionModes_.isEmpty()) {
        if (evolutionModes_.isEmpty()) {
          evolutionModes_ = other.evolutionModes_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureEvolutionModesIsMutable();
          evolutionModes_.addAll(other.evolutionModes_);
        }
        onChanged();
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
      io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Integer> evolutionModes_ =
      java.util.Collections.emptyList();
    private void ensureEvolutionModesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        evolutionModes_ = new java.util.ArrayList<java.lang.Integer>(evolutionModes_);
        bitField0_ |= 0x00000001;
      }
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @return A list containing the evolutionModes.
     */
    public java.util.List<io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode> getEvolutionModesList() {
      return new com.google.protobuf.Internal.ListAdapter<
          java.lang.Integer, io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode>(evolutionModes_, evolutionModes_converter_);
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @return The count of evolutionModes.
     */
    public int getEvolutionModesCount() {
      return evolutionModes_.size();
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param index The index of the element to return.
     * @return The evolutionModes at the given index.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode getEvolutionModes(int index) {
      return evolutionModes_converter_.convert(evolutionModes_.get(index));
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param index The index to set the value at.
     * @param value The evolutionModes to set.
     * @return This builder for chaining.
     */
    public Builder setEvolutionModes(
        int index, io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureEvolutionModesIsMutable();
      evolutionModes_.set(index, value.getNumber());
      onChanged();
      return this;
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param value The evolutionModes to add.
     * @return This builder for chaining.
     */
    public Builder addEvolutionModes(io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureEvolutionModesIsMutable();
      evolutionModes_.add(value.getNumber());
      onChanged();
      return this;
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param values The evolutionModes to add.
     * @return This builder for chaining.
     */
    public Builder addAllEvolutionModes(
        java.lang.Iterable<? extends io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode> values) {
      ensureEvolutionModesIsMutable();
      for (io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode value : values) {
        evolutionModes_.add(value.getNumber());
      }
      onChanged();
      return this;
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearEvolutionModes() {
      evolutionModes_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @return A list containing the enum numeric values on the wire for evolutionModes.
     */
    public java.util.List<java.lang.Integer>
    getEvolutionModesValueList() {
      return java.util.Collections.unmodifiableList(evolutionModes_);
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of evolutionModes at the given index.
     */
    public int getEvolutionModesValue(int index) {
      return evolutionModes_.get(index);
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of evolutionModes at the given index.
     * @return This builder for chaining.
     */
    public Builder setEvolutionModesValue(
        int index, int value) {
      ensureEvolutionModesIsMutable();
      evolutionModes_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param value The enum numeric value on the wire for evolutionModes to add.
     * @return This builder for chaining.
     */
    public Builder addEvolutionModesValue(int value) {
      ensureEvolutionModesIsMutable();
      evolutionModes_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated .io.evitadb.externalApi.grpc.generated.GrpcCatalogEvolutionMode evolutionModes = 1;</code>
     * @param values The enum numeric values on the wire for evolutionModes to add.
     * @return This builder for chaining.
     */
    public Builder addAllEvolutionModesValue(
        java.lang.Iterable<java.lang.Integer> values) {
      ensureEvolutionModesIsMutable();
      for (int value : values) {
        evolutionModes_.add(value);
      }
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation)
  private static final io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcAllowEvolutionModeInCatalogSchemaMutation>
      PARSER = new com.google.protobuf.AbstractParser<GrpcAllowEvolutionModeInCatalogSchemaMutation>() {
    @java.lang.Override
    public GrpcAllowEvolutionModeInCatalogSchemaMutation parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcAllowEvolutionModeInCatalogSchemaMutation(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcAllowEvolutionModeInCatalogSchemaMutation> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcAllowEvolutionModeInCatalogSchemaMutation> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcAllowEvolutionModeInCatalogSchemaMutation getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
