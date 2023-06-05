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
// source: GrpcAssociatedDataMutations.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation}
 */
public final class GrpcUpsertAssociatedDataMutation extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation)
    GrpcUpsertAssociatedDataMutationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcUpsertAssociatedDataMutation.newBuilder() to construct.
  private GrpcUpsertAssociatedDataMutation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcUpsertAssociatedDataMutation() {
    associatedDataName_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcUpsertAssociatedDataMutation();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcUpsertAssociatedDataMutation(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            associatedDataName_ = s;
            break;
          }
          case 18: {
            io.evitadb.externalApi.grpc.generated.GrpcLocale.Builder subBuilder = null;
            if (associatedDataLocale_ != null) {
              subBuilder = associatedDataLocale_.toBuilder();
            }
            associatedDataLocale_ = input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcLocale.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(associatedDataLocale_);
              associatedDataLocale_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.Builder subBuilder = null;
            if (associatedDataValue_ != null) {
              subBuilder = associatedDataValue_.toBuilder();
            }
            associatedDataValue_ = input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(associatedDataValue_);
              associatedDataValue_ = subBuilder.buildPartial();
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
    return io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpsertAssociatedDataMutation_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpsertAssociatedDataMutation_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation.class, io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation.Builder.class);
  }

  public static final int ASSOCIATEDDATANAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object associatedDataName_;
  /**
   * <code>string associatedDataName = 1;</code>
   * @return The associatedDataName.
   */
  @java.lang.Override
  public java.lang.String getAssociatedDataName() {
    java.lang.Object ref = associatedDataName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      associatedDataName_ = s;
      return s;
    }
  }
  /**
   * <code>string associatedDataName = 1;</code>
   * @return The bytes for associatedDataName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAssociatedDataNameBytes() {
    java.lang.Object ref = associatedDataName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      associatedDataName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ASSOCIATEDDATALOCALE_FIELD_NUMBER = 2;
  private io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale_;
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
   * @return Whether the associatedDataLocale field is set.
   */
  @java.lang.Override
  public boolean hasAssociatedDataLocale() {
    return associatedDataLocale_ != null;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
   * @return The associatedDataLocale.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcLocale getAssociatedDataLocale() {
    return associatedDataLocale_ == null ? io.evitadb.externalApi.grpc.generated.GrpcLocale.getDefaultInstance() : associatedDataLocale_;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcLocaleOrBuilder getAssociatedDataLocaleOrBuilder() {
    return getAssociatedDataLocale();
  }

  public static final int ASSOCIATEDDATAVALUE_FIELD_NUMBER = 3;
  private io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue_;
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
   * @return Whether the associatedDataValue field is set.
   */
  @java.lang.Override
  public boolean hasAssociatedDataValue() {
    return associatedDataValue_ != null;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
   * @return The associatedDataValue.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue getAssociatedDataValue() {
    return associatedDataValue_ == null ? io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.getDefaultInstance() : associatedDataValue_;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValueOrBuilder getAssociatedDataValueOrBuilder() {
    return getAssociatedDataValue();
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(associatedDataName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, associatedDataName_);
    }
    if (associatedDataLocale_ != null) {
      output.writeMessage(2, getAssociatedDataLocale());
    }
    if (associatedDataValue_ != null) {
      output.writeMessage(3, getAssociatedDataValue());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(associatedDataName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, associatedDataName_);
    }
    if (associatedDataLocale_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAssociatedDataLocale());
    }
    if (associatedDataValue_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getAssociatedDataValue());
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation other = (io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation) obj;

    if (!getAssociatedDataName()
        .equals(other.getAssociatedDataName())) return false;
    if (hasAssociatedDataLocale() != other.hasAssociatedDataLocale()) return false;
    if (hasAssociatedDataLocale()) {
      if (!getAssociatedDataLocale()
          .equals(other.getAssociatedDataLocale())) return false;
    }
    if (hasAssociatedDataValue() != other.hasAssociatedDataValue()) return false;
    if (hasAssociatedDataValue()) {
      if (!getAssociatedDataValue()
          .equals(other.getAssociatedDataValue())) return false;
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
    hash = (37 * hash) + ASSOCIATEDDATANAME_FIELD_NUMBER;
    hash = (53 * hash) + getAssociatedDataName().hashCode();
    if (hasAssociatedDataLocale()) {
      hash = (37 * hash) + ASSOCIATEDDATALOCALE_FIELD_NUMBER;
      hash = (53 * hash) + getAssociatedDataLocale().hashCode();
    }
    if (hasAssociatedDataValue()) {
      hash = (37 * hash) + ASSOCIATEDDATAVALUE_FIELD_NUMBER;
      hash = (53 * hash) + getAssociatedDataValue().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation prototype) {
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
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation)
      io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpsertAssociatedDataMutation_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpsertAssociatedDataMutation_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation.class, io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation.newBuilder()
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
      associatedDataName_ = "";

      if (associatedDataLocaleBuilder_ == null) {
        associatedDataLocale_ = null;
      } else {
        associatedDataLocale_ = null;
        associatedDataLocaleBuilder_ = null;
      }
      if (associatedDataValueBuilder_ == null) {
        associatedDataValue_ = null;
      } else {
        associatedDataValue_ = null;
        associatedDataValueBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcAssociatedDataMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpsertAssociatedDataMutation_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation build() {
      io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation result = new io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation(this);
      result.associatedDataName_ = associatedDataName_;
      if (associatedDataLocaleBuilder_ == null) {
        result.associatedDataLocale_ = associatedDataLocale_;
      } else {
        result.associatedDataLocale_ = associatedDataLocaleBuilder_.build();
      }
      if (associatedDataValueBuilder_ == null) {
        result.associatedDataValue_ = associatedDataValue_;
      } else {
        result.associatedDataValue_ = associatedDataValueBuilder_.build();
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation.getDefaultInstance()) return this;
      if (!other.getAssociatedDataName().isEmpty()) {
        associatedDataName_ = other.associatedDataName_;
        onChanged();
      }
      if (other.hasAssociatedDataLocale()) {
        mergeAssociatedDataLocale(other.getAssociatedDataLocale());
      }
      if (other.hasAssociatedDataValue()) {
        mergeAssociatedDataValue(other.getAssociatedDataValue());
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
      io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object associatedDataName_ = "";
    /**
     * <code>string associatedDataName = 1;</code>
     * @return The associatedDataName.
     */
    public java.lang.String getAssociatedDataName() {
      java.lang.Object ref = associatedDataName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        associatedDataName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string associatedDataName = 1;</code>
     * @return The bytes for associatedDataName.
     */
    public com.google.protobuf.ByteString
        getAssociatedDataNameBytes() {
      java.lang.Object ref = associatedDataName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        associatedDataName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string associatedDataName = 1;</code>
     * @param value The associatedDataName to set.
     * @return This builder for chaining.
     */
    public Builder setAssociatedDataName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      associatedDataName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string associatedDataName = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearAssociatedDataName() {

      associatedDataName_ = getDefaultInstance().getAssociatedDataName();
      onChanged();
      return this;
    }
    /**
     * <code>string associatedDataName = 1;</code>
     * @param value The bytes for associatedDataName to set.
     * @return This builder for chaining.
     */
    public Builder setAssociatedDataNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      associatedDataName_ = value;
      onChanged();
      return this;
    }

    private io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcLocale, io.evitadb.externalApi.grpc.generated.GrpcLocale.Builder, io.evitadb.externalApi.grpc.generated.GrpcLocaleOrBuilder> associatedDataLocaleBuilder_;
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     * @return Whether the associatedDataLocale field is set.
     */
    public boolean hasAssociatedDataLocale() {
      return associatedDataLocaleBuilder_ != null || associatedDataLocale_ != null;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     * @return The associatedDataLocale.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcLocale getAssociatedDataLocale() {
      if (associatedDataLocaleBuilder_ == null) {
        return associatedDataLocale_ == null ? io.evitadb.externalApi.grpc.generated.GrpcLocale.getDefaultInstance() : associatedDataLocale_;
      } else {
        return associatedDataLocaleBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     */
    public Builder setAssociatedDataLocale(io.evitadb.externalApi.grpc.generated.GrpcLocale value) {
      if (associatedDataLocaleBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        associatedDataLocale_ = value;
        onChanged();
      } else {
        associatedDataLocaleBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     */
    public Builder setAssociatedDataLocale(
        io.evitadb.externalApi.grpc.generated.GrpcLocale.Builder builderForValue) {
      if (associatedDataLocaleBuilder_ == null) {
        associatedDataLocale_ = builderForValue.build();
        onChanged();
      } else {
        associatedDataLocaleBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     */
    public Builder mergeAssociatedDataLocale(io.evitadb.externalApi.grpc.generated.GrpcLocale value) {
      if (associatedDataLocaleBuilder_ == null) {
        if (associatedDataLocale_ != null) {
          associatedDataLocale_ =
            io.evitadb.externalApi.grpc.generated.GrpcLocale.newBuilder(associatedDataLocale_).mergeFrom(value).buildPartial();
        } else {
          associatedDataLocale_ = value;
        }
        onChanged();
      } else {
        associatedDataLocaleBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     */
    public Builder clearAssociatedDataLocale() {
      if (associatedDataLocaleBuilder_ == null) {
        associatedDataLocale_ = null;
        onChanged();
      } else {
        associatedDataLocale_ = null;
        associatedDataLocaleBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcLocale.Builder getAssociatedDataLocaleBuilder() {

      onChanged();
      return getAssociatedDataLocaleFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcLocaleOrBuilder getAssociatedDataLocaleOrBuilder() {
      if (associatedDataLocaleBuilder_ != null) {
        return associatedDataLocaleBuilder_.getMessageOrBuilder();
      } else {
        return associatedDataLocale_ == null ?
            io.evitadb.externalApi.grpc.generated.GrpcLocale.getDefaultInstance() : associatedDataLocale_;
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcLocale associatedDataLocale = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcLocale, io.evitadb.externalApi.grpc.generated.GrpcLocale.Builder, io.evitadb.externalApi.grpc.generated.GrpcLocaleOrBuilder>
        getAssociatedDataLocaleFieldBuilder() {
      if (associatedDataLocaleBuilder_ == null) {
        associatedDataLocaleBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcLocale, io.evitadb.externalApi.grpc.generated.GrpcLocale.Builder, io.evitadb.externalApi.grpc.generated.GrpcLocaleOrBuilder>(
                getAssociatedDataLocale(),
                getParentForChildren(),
                isClean());
        associatedDataLocale_ = null;
      }
      return associatedDataLocaleBuilder_;
    }

    private io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue, io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.Builder, io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValueOrBuilder> associatedDataValueBuilder_;
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     * @return Whether the associatedDataValue field is set.
     */
    public boolean hasAssociatedDataValue() {
      return associatedDataValueBuilder_ != null || associatedDataValue_ != null;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     * @return The associatedDataValue.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue getAssociatedDataValue() {
      if (associatedDataValueBuilder_ == null) {
        return associatedDataValue_ == null ? io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.getDefaultInstance() : associatedDataValue_;
      } else {
        return associatedDataValueBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     */
    public Builder setAssociatedDataValue(io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue value) {
      if (associatedDataValueBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        associatedDataValue_ = value;
        onChanged();
      } else {
        associatedDataValueBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     */
    public Builder setAssociatedDataValue(
        io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.Builder builderForValue) {
      if (associatedDataValueBuilder_ == null) {
        associatedDataValue_ = builderForValue.build();
        onChanged();
      } else {
        associatedDataValueBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     */
    public Builder mergeAssociatedDataValue(io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue value) {
      if (associatedDataValueBuilder_ == null) {
        if (associatedDataValue_ != null) {
          associatedDataValue_ =
            io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.newBuilder(associatedDataValue_).mergeFrom(value).buildPartial();
        } else {
          associatedDataValue_ = value;
        }
        onChanged();
      } else {
        associatedDataValueBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     */
    public Builder clearAssociatedDataValue() {
      if (associatedDataValueBuilder_ == null) {
        associatedDataValue_ = null;
        onChanged();
      } else {
        associatedDataValue_ = null;
        associatedDataValueBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.Builder getAssociatedDataValueBuilder() {

      onChanged();
      return getAssociatedDataValueFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValueOrBuilder getAssociatedDataValueOrBuilder() {
      if (associatedDataValueBuilder_ != null) {
        return associatedDataValueBuilder_.getMessageOrBuilder();
      } else {
        return associatedDataValue_ == null ?
            io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.getDefaultInstance() : associatedDataValue_;
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue associatedDataValue = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue, io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.Builder, io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValueOrBuilder>
        getAssociatedDataValueFieldBuilder() {
      if (associatedDataValueBuilder_ == null) {
        associatedDataValueBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue, io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValue.Builder, io.evitadb.externalApi.grpc.generated.GrpcEvitaAssociatedDataValueOrBuilder>(
                getAssociatedDataValue(),
                getParentForChildren(),
                isClean());
        associatedDataValue_ = null;
      }
      return associatedDataValueBuilder_;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation)
  private static final io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcUpsertAssociatedDataMutation>
      PARSER = new com.google.protobuf.AbstractParser<GrpcUpsertAssociatedDataMutation>() {
    @java.lang.Override
    public GrpcUpsertAssociatedDataMutation parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcUpsertAssociatedDataMutation(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcUpsertAssociatedDataMutation> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcUpsertAssociatedDataMutation> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcUpsertAssociatedDataMutation getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

