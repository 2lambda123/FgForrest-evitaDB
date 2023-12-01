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
// source: GrpcEvitaSessionAPI.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * <pre>
 * Request for updating the schema of an existing entity type.
 * </pre>
 *
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest}
 */
public final class GrpcUpdateEntitySchemaRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest)
    GrpcUpdateEntitySchemaRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcUpdateEntitySchemaRequest.newBuilder() to construct.
  private GrpcUpdateEntitySchemaRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcUpdateEntitySchemaRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcUpdateEntitySchemaRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcUpdateEntitySchemaRequest(
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
            io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.Builder subBuilder = null;
            if (schemaMutation_ != null) {
              subBuilder = schemaMutation_.toBuilder();
            }
            schemaMutation_ = input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(schemaMutation_);
              schemaMutation_ = subBuilder.buildPartial();
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
    return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpdateEntitySchemaRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpdateEntitySchemaRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest.class, io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest.Builder.class);
  }

  public static final int SCHEMAMUTATION_FIELD_NUMBER = 1;
  private io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation_;
  /**
   * <pre>
   * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
   * @return Whether the schemaMutation field is set.
   */
  @java.lang.Override
  public boolean hasSchemaMutation() {
    return schemaMutation_ != null;
  }
  /**
   * <pre>
   * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
   * @return The schemaMutation.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation getSchemaMutation() {
    return schemaMutation_ == null ? io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.getDefaultInstance() : schemaMutation_;
  }
  /**
   * <pre>
   * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutationOrBuilder getSchemaMutationOrBuilder() {
    return getSchemaMutation();
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
    if (schemaMutation_ != null) {
      output.writeMessage(1, getSchemaMutation());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (schemaMutation_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getSchemaMutation());
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest other = (io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest) obj;

    if (hasSchemaMutation() != other.hasSchemaMutation()) return false;
    if (hasSchemaMutation()) {
      if (!getSchemaMutation()
          .equals(other.getSchemaMutation())) return false;
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
    if (hasSchemaMutation()) {
      hash = (37 * hash) + SCHEMAMUTATION_FIELD_NUMBER;
      hash = (53 * hash) + getSchemaMutation().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest prototype) {
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
   * <pre>
   * Request for updating the schema of an existing entity type.
   * </pre>
   *
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest)
      io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpdateEntitySchemaRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpdateEntitySchemaRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest.class, io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest.newBuilder()
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
      if (schemaMutationBuilder_ == null) {
        schemaMutation_ = null;
      } else {
        schemaMutation_ = null;
        schemaMutationBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcUpdateEntitySchemaRequest_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest build() {
      io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest result = new io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest(this);
      if (schemaMutationBuilder_ == null) {
        result.schemaMutation_ = schemaMutation_;
      } else {
        result.schemaMutation_ = schemaMutationBuilder_.build();
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest.getDefaultInstance()) return this;
      if (other.hasSchemaMutation()) {
        mergeSchemaMutation(other.getSchemaMutation());
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
      io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation, io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutationOrBuilder> schemaMutationBuilder_;
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     * @return Whether the schemaMutation field is set.
     */
    public boolean hasSchemaMutation() {
      return schemaMutationBuilder_ != null || schemaMutation_ != null;
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     * @return The schemaMutation.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation getSchemaMutation() {
      if (schemaMutationBuilder_ == null) {
        return schemaMutation_ == null ? io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.getDefaultInstance() : schemaMutation_;
      } else {
        return schemaMutationBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     */
    public Builder setSchemaMutation(io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation value) {
      if (schemaMutationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        schemaMutation_ = value;
        onChanged();
      } else {
        schemaMutationBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     */
    public Builder setSchemaMutation(
        io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.Builder builderForValue) {
      if (schemaMutationBuilder_ == null) {
        schemaMutation_ = builderForValue.build();
        onChanged();
      } else {
        schemaMutationBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     */
    public Builder mergeSchemaMutation(io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation value) {
      if (schemaMutationBuilder_ == null) {
        if (schemaMutation_ != null) {
          schemaMutation_ =
            io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.newBuilder(schemaMutation_).mergeFrom(value).buildPartial();
        } else {
          schemaMutation_ = value;
        }
        onChanged();
      } else {
        schemaMutationBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     */
    public Builder clearSchemaMutation() {
      if (schemaMutationBuilder_ == null) {
        schemaMutation_ = null;
        onChanged();
      } else {
        schemaMutation_ = null;
        schemaMutationBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.Builder getSchemaMutationBuilder() {

      onChanged();
      return getSchemaMutationFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutationOrBuilder getSchemaMutationOrBuilder() {
      if (schemaMutationBuilder_ != null) {
        return schemaMutationBuilder_.getMessageOrBuilder();
      } else {
        return schemaMutation_ == null ?
            io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.getDefaultInstance() : schemaMutation_;
      }
    }
    /**
     * <pre>
     * Wrapper that holds the entity type and the collection of EntitySchemaMutations to be applied.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation schemaMutation = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation, io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutationOrBuilder>
        getSchemaMutationFieldBuilder() {
      if (schemaMutationBuilder_ == null) {
        schemaMutationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation, io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcModifyEntitySchemaMutationOrBuilder>(
                getSchemaMutation(),
                getParentForChildren(),
                isClean());
        schemaMutation_ = null;
      }
      return schemaMutationBuilder_;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest)
  private static final io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcUpdateEntitySchemaRequest>
      PARSER = new com.google.protobuf.AbstractParser<GrpcUpdateEntitySchemaRequest>() {
    @java.lang.Override
    public GrpcUpdateEntitySchemaRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcUpdateEntitySchemaRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcUpdateEntitySchemaRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcUpdateEntitySchemaRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcUpdateEntitySchemaRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

