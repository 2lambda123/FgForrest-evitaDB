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
// source: GrpcSortableAttributeCompoundSchemaMutations.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * <pre>
 * Mutation is responsible for setting value to a `SortableAttributeCompoundSchema.description` in `EntitySchema` or
 * `ReferenceSchema`.
 * </pre>
 *
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation}
 */
public final class GrpcModifySortableAttributeCompoundSchemaDescriptionMutation extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation)
    GrpcModifySortableAttributeCompoundSchemaDescriptionMutationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.newBuilder() to construct.
  private GrpcModifySortableAttributeCompoundSchemaDescriptionMutation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcModifySortableAttributeCompoundSchemaDescriptionMutation() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcModifySortableAttributeCompoundSchemaDescriptionMutation();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcModifySortableAttributeCompoundSchemaDescriptionMutation(
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

            name_ = s;
            break;
          }
          case 18: {
            com.google.protobuf.StringValue.Builder subBuilder = null;
            if (description_ != null) {
              subBuilder = description_.toBuilder();
            }
            description_ = input.readMessage(com.google.protobuf.StringValue.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(description_);
              description_ = subBuilder.buildPartial();
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
    return io.evitadb.externalApi.grpc.generated.GrpcSortableAttributeCompoundSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcModifySortableAttributeCompoundSchemaDescriptionMutation_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcSortableAttributeCompoundSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcModifySortableAttributeCompoundSchemaDescriptionMutation_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.class, io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   * Name of the sortable attribute compound the mutation is targeting.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Name of the sortable attribute compound the mutation is targeting.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESCRIPTION_FIELD_NUMBER = 2;
  private com.google.protobuf.StringValue description_;
  /**
   * <pre>
   * Contains description of the model is optional but helps authors of the schema / client API to better
   * explain the original purpose of the model to the consumers.
   * </pre>
   *
   * <code>.google.protobuf.StringValue description = 2;</code>
   * @return Whether the description field is set.
   */
  @java.lang.Override
  public boolean hasDescription() {
    return description_ != null;
  }
  /**
   * <pre>
   * Contains description of the model is optional but helps authors of the schema / client API to better
   * explain the original purpose of the model to the consumers.
   * </pre>
   *
   * <code>.google.protobuf.StringValue description = 2;</code>
   * @return The description.
   */
  @java.lang.Override
  public com.google.protobuf.StringValue getDescription() {
    return description_ == null ? com.google.protobuf.StringValue.getDefaultInstance() : description_;
  }
  /**
   * <pre>
   * Contains description of the model is optional but helps authors of the schema / client API to better
   * explain the original purpose of the model to the consumers.
   * </pre>
   *
   * <code>.google.protobuf.StringValue description = 2;</code>
   */
  @java.lang.Override
  public com.google.protobuf.StringValueOrBuilder getDescriptionOrBuilder() {
    return getDescription();
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
    }
    if (description_ != null) {
      output.writeMessage(2, getDescription());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
    }
    if (description_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getDescription());
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation other = (io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation) obj;

    if (!getName()
        .equals(other.getName())) return false;
    if (hasDescription() != other.hasDescription()) return false;
    if (hasDescription()) {
      if (!getDescription()
          .equals(other.getDescription())) return false;
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
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    if (hasDescription()) {
      hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
      hash = (53 * hash) + getDescription().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation prototype) {
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
   * Mutation is responsible for setting value to a `SortableAttributeCompoundSchema.description` in `EntitySchema` or
   * `ReferenceSchema`.
   * </pre>
   *
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation)
      io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcSortableAttributeCompoundSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcModifySortableAttributeCompoundSchemaDescriptionMutation_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcSortableAttributeCompoundSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcModifySortableAttributeCompoundSchemaDescriptionMutation_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.class, io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.newBuilder()
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
      name_ = "";

      if (descriptionBuilder_ == null) {
        description_ = null;
      } else {
        description_ = null;
        descriptionBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcSortableAttributeCompoundSchemaMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcModifySortableAttributeCompoundSchemaDescriptionMutation_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation build() {
      io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation result = new io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation(this);
      result.name_ = name_;
      if (descriptionBuilder_ == null) {
        result.description_ = description_;
      } else {
        result.description_ = descriptionBuilder_.build();
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.hasDescription()) {
        mergeDescription(other.getDescription());
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
      io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <pre>
     * Name of the sortable attribute compound the mutation is targeting.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Name of the sortable attribute compound the mutation is targeting.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Name of the sortable attribute compound the mutation is targeting.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Name of the sortable attribute compound the mutation is targeting.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {

      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Name of the sortable attribute compound the mutation is targeting.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      name_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.StringValue description_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.StringValue, com.google.protobuf.StringValue.Builder, com.google.protobuf.StringValueOrBuilder> descriptionBuilder_;
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     * @return Whether the description field is set.
     */
    public boolean hasDescription() {
      return descriptionBuilder_ != null || description_ != null;
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     * @return The description.
     */
    public com.google.protobuf.StringValue getDescription() {
      if (descriptionBuilder_ == null) {
        return description_ == null ? com.google.protobuf.StringValue.getDefaultInstance() : description_;
      } else {
        return descriptionBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     */
    public Builder setDescription(com.google.protobuf.StringValue value) {
      if (descriptionBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        description_ = value;
        onChanged();
      } else {
        descriptionBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     */
    public Builder setDescription(
        com.google.protobuf.StringValue.Builder builderForValue) {
      if (descriptionBuilder_ == null) {
        description_ = builderForValue.build();
        onChanged();
      } else {
        descriptionBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     */
    public Builder mergeDescription(com.google.protobuf.StringValue value) {
      if (descriptionBuilder_ == null) {
        if (description_ != null) {
          description_ =
            com.google.protobuf.StringValue.newBuilder(description_).mergeFrom(value).buildPartial();
        } else {
          description_ = value;
        }
        onChanged();
      } else {
        descriptionBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     */
    public Builder clearDescription() {
      if (descriptionBuilder_ == null) {
        description_ = null;
        onChanged();
      } else {
        description_ = null;
        descriptionBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     */
    public com.google.protobuf.StringValue.Builder getDescriptionBuilder() {

      onChanged();
      return getDescriptionFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     */
    public com.google.protobuf.StringValueOrBuilder getDescriptionOrBuilder() {
      if (descriptionBuilder_ != null) {
        return descriptionBuilder_.getMessageOrBuilder();
      } else {
        return description_ == null ?
            com.google.protobuf.StringValue.getDefaultInstance() : description_;
      }
    }
    /**
     * <pre>
     * Contains description of the model is optional but helps authors of the schema / client API to better
     * explain the original purpose of the model to the consumers.
     * </pre>
     *
     * <code>.google.protobuf.StringValue description = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.StringValue, com.google.protobuf.StringValue.Builder, com.google.protobuf.StringValueOrBuilder>
        getDescriptionFieldBuilder() {
      if (descriptionBuilder_ == null) {
        descriptionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.StringValue, com.google.protobuf.StringValue.Builder, com.google.protobuf.StringValueOrBuilder>(
                getDescription(),
                getParentForChildren(),
                isClean());
        description_ = null;
      }
      return descriptionBuilder_;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation)
  private static final io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcModifySortableAttributeCompoundSchemaDescriptionMutation>
      PARSER = new com.google.protobuf.AbstractParser<GrpcModifySortableAttributeCompoundSchemaDescriptionMutation>() {
    @java.lang.Override
    public GrpcModifySortableAttributeCompoundSchemaDescriptionMutation parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcModifySortableAttributeCompoundSchemaDescriptionMutation(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcModifySortableAttributeCompoundSchemaDescriptionMutation> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcModifySortableAttributeCompoundSchemaDescriptionMutation> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcModifySortableAttributeCompoundSchemaDescriptionMutation getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

