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
// source: GrpcEntity.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent}
 */
public final class GrpcEntityReferenceWithParent extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent)
    GrpcEntityReferenceWithParentOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcEntityReferenceWithParent.newBuilder() to construct.
  private GrpcEntityReferenceWithParent(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcEntityReferenceWithParent() {
    entityType_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcEntityReferenceWithParent();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcEntityReferenceWithParent(
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

            entityType_ = s;
            break;
          }
          case 16: {

            primaryKey_ = input.readInt32();
            break;
          }
          case 24: {

            version_ = input.readInt32();
            break;
          }
          case 34: {
            io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder subBuilder = null;
            if (parent_ != null) {
              subBuilder = parent_.toBuilder();
            }
            parent_ = input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(parent_);
              parent_ = subBuilder.buildPartial();
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
    return io.evitadb.externalApi.grpc.generated.GrpcEntity.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityReferenceWithParent_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcEntity.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityReferenceWithParent_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.class, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder.class);
  }

  public static final int ENTITYTYPE_FIELD_NUMBER = 1;
  private volatile java.lang.Object entityType_;
  /**
   * <code>string entityType = 1;</code>
   * @return The entityType.
   */
  @java.lang.Override
  public java.lang.String getEntityType() {
    java.lang.Object ref = entityType_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      entityType_ = s;
      return s;
    }
  }
  /**
   * <code>string entityType = 1;</code>
   * @return The bytes for entityType.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getEntityTypeBytes() {
    java.lang.Object ref = entityType_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      entityType_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRIMARYKEY_FIELD_NUMBER = 2;
  private int primaryKey_;
  /**
   * <code>int32 primaryKey = 2;</code>
   * @return The primaryKey.
   */
  @java.lang.Override
  public int getPrimaryKey() {
    return primaryKey_;
  }

  public static final int VERSION_FIELD_NUMBER = 3;
  private int version_;
  /**
   * <code>int32 version = 3;</code>
   * @return The version.
   */
  @java.lang.Override
  public int getVersion() {
    return version_;
  }

  public static final int PARENT_FIELD_NUMBER = 4;
  private io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent_;
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
   * @return Whether the parent field is set.
   */
  @java.lang.Override
  public boolean hasParent() {
    return parent_ != null;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
   * @return The parent.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent getParent() {
    return parent_ == null ? io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.getDefaultInstance() : parent_;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParentOrBuilder getParentOrBuilder() {
    return getParent();
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(entityType_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, entityType_);
    }
    if (primaryKey_ != 0) {
      output.writeInt32(2, primaryKey_);
    }
    if (version_ != 0) {
      output.writeInt32(3, version_);
    }
    if (parent_ != null) {
      output.writeMessage(4, getParent());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(entityType_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, entityType_);
    }
    if (primaryKey_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, primaryKey_);
    }
    if (version_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, version_);
    }
    if (parent_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getParent());
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent other = (io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent) obj;

    if (!getEntityType()
        .equals(other.getEntityType())) return false;
    if (getPrimaryKey()
        != other.getPrimaryKey()) return false;
    if (getVersion()
        != other.getVersion()) return false;
    if (hasParent() != other.hasParent()) return false;
    if (hasParent()) {
      if (!getParent()
          .equals(other.getParent())) return false;
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
    hash = (37 * hash) + ENTITYTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getEntityType().hashCode();
    hash = (37 * hash) + PRIMARYKEY_FIELD_NUMBER;
    hash = (53 * hash) + getPrimaryKey();
    hash = (37 * hash) + VERSION_FIELD_NUMBER;
    hash = (53 * hash) + getVersion();
    if (hasParent()) {
      hash = (37 * hash) + PARENT_FIELD_NUMBER;
      hash = (53 * hash) + getParent().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent prototype) {
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
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent)
      io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParentOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntity.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityReferenceWithParent_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntity.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityReferenceWithParent_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.class, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.newBuilder()
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
      entityType_ = "";

      primaryKey_ = 0;

      version_ = 0;

      if (parentBuilder_ == null) {
        parent_ = null;
      } else {
        parent_ = null;
        parentBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntity.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityReferenceWithParent_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent build() {
      io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent result = new io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent(this);
      result.entityType_ = entityType_;
      result.primaryKey_ = primaryKey_;
      result.version_ = version_;
      if (parentBuilder_ == null) {
        result.parent_ = parent_;
      } else {
        result.parent_ = parentBuilder_.build();
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.getDefaultInstance()) return this;
      if (!other.getEntityType().isEmpty()) {
        entityType_ = other.entityType_;
        onChanged();
      }
      if (other.getPrimaryKey() != 0) {
        setPrimaryKey(other.getPrimaryKey());
      }
      if (other.getVersion() != 0) {
        setVersion(other.getVersion());
      }
      if (other.hasParent()) {
        mergeParent(other.getParent());
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
      io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object entityType_ = "";
    /**
     * <code>string entityType = 1;</code>
     * @return The entityType.
     */
    public java.lang.String getEntityType() {
      java.lang.Object ref = entityType_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        entityType_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string entityType = 1;</code>
     * @return The bytes for entityType.
     */
    public com.google.protobuf.ByteString
        getEntityTypeBytes() {
      java.lang.Object ref = entityType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        entityType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string entityType = 1;</code>
     * @param value The entityType to set.
     * @return This builder for chaining.
     */
    public Builder setEntityType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      entityType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string entityType = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearEntityType() {

      entityType_ = getDefaultInstance().getEntityType();
      onChanged();
      return this;
    }
    /**
     * <code>string entityType = 1;</code>
     * @param value The bytes for entityType to set.
     * @return This builder for chaining.
     */
    public Builder setEntityTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      entityType_ = value;
      onChanged();
      return this;
    }

    private int primaryKey_ ;
    /**
     * <code>int32 primaryKey = 2;</code>
     * @return The primaryKey.
     */
    @java.lang.Override
    public int getPrimaryKey() {
      return primaryKey_;
    }
    /**
     * <code>int32 primaryKey = 2;</code>
     * @param value The primaryKey to set.
     * @return This builder for chaining.
     */
    public Builder setPrimaryKey(int value) {

      primaryKey_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 primaryKey = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPrimaryKey() {

      primaryKey_ = 0;
      onChanged();
      return this;
    }

    private int version_ ;
    /**
     * <code>int32 version = 3;</code>
     * @return The version.
     */
    @java.lang.Override
    public int getVersion() {
      return version_;
    }
    /**
     * <code>int32 version = 3;</code>
     * @param value The version to set.
     * @return This builder for chaining.
     */
    public Builder setVersion(int value) {

      version_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 version = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearVersion() {

      version_ = 0;
      onChanged();
      return this;
    }

    private io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParentOrBuilder> parentBuilder_;
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     * @return Whether the parent field is set.
     */
    public boolean hasParent() {
      return parentBuilder_ != null || parent_ != null;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     * @return The parent.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent getParent() {
      if (parentBuilder_ == null) {
        return parent_ == null ? io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.getDefaultInstance() : parent_;
      } else {
        return parentBuilder_.getMessage();
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     */
    public Builder setParent(io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent value) {
      if (parentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        parent_ = value;
        onChanged();
      } else {
        parentBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     */
    public Builder setParent(
        io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder builderForValue) {
      if (parentBuilder_ == null) {
        parent_ = builderForValue.build();
        onChanged();
      } else {
        parentBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     */
    public Builder mergeParent(io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent value) {
      if (parentBuilder_ == null) {
        if (parent_ != null) {
          parent_ =
            io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.newBuilder(parent_).mergeFrom(value).buildPartial();
        } else {
          parent_ = value;
        }
        onChanged();
      } else {
        parentBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     */
    public Builder clearParent() {
      if (parentBuilder_ == null) {
        parent_ = null;
        onChanged();
      } else {
        parent_ = null;
        parentBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder getParentBuilder() {

      onChanged();
      return getParentFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParentOrBuilder getParentOrBuilder() {
      if (parentBuilder_ != null) {
        return parentBuilder_.getMessageOrBuilder();
      } else {
        return parent_ == null ?
            io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.getDefaultInstance() : parent_;
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent parent = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParentOrBuilder>
        getParentFieldBuilder() {
      if (parentBuilder_ == null) {
        parentBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParentOrBuilder>(
                getParent(),
                getParentForChildren(),
                isClean());
        parent_ = null;
      }
      return parentBuilder_;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent)
  private static final io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcEntityReferenceWithParent>
      PARSER = new com.google.protobuf.AbstractParser<GrpcEntityReferenceWithParent>() {
    @java.lang.Override
    public GrpcEntityReferenceWithParent parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcEntityReferenceWithParent(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcEntityReferenceWithParent> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcEntityReferenceWithParent> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityReferenceWithParent getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

