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
// source: GrpcReferenceMutations.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue}
 */
public final class GrpcLocalizedAttributeMutationValue extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue)
    GrpcLocalizedAttributeMutationValueOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcLocalizedAttributeMutationValue.newBuilder() to construct.
  private GrpcLocalizedAttributeMutationValue(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcLocalizedAttributeMutationValue() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcLocalizedAttributeMutationValue();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcLocalizedAttributeMutationValue(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              attributes_ = com.google.protobuf.MapField.newMapField(
                  AttributesDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000001;
            }
            com.google.protobuf.MapEntry<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue>
            attributes__ = input.readMessage(
                AttributesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            attributes_.getMutableMap().put(
                attributes__.getKey(), attributes__.getValue());
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
    return io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalizedAttributeMutationValue_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 1:
        return internalGetAttributes();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalizedAttributeMutationValue_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue.class, io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue.Builder.class);
  }

  public static final int ATTRIBUTES_FIELD_NUMBER = 1;
  private static final class AttributesDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue>newDefaultInstance(
                io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalizedAttributeMutationValue_AttributesEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.MESSAGE,
                io.evitadb.externalApi.grpc.generated.GrpcEvitaValue.getDefaultInstance());
  }
  private com.google.protobuf.MapField<
      java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> attributes_;
  private com.google.protobuf.MapField<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue>
  internalGetAttributes() {
    if (attributes_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          AttributesDefaultEntryHolder.defaultEntry);
    }
    return attributes_;
  }

  public int getAttributesCount() {
    return internalGetAttributes().getMap().size();
  }
  /**
   * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
   */

  @java.lang.Override
  public boolean containsAttributes(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    return internalGetAttributes().getMap().containsKey(key);
  }
  /**
   * Use {@link #getAttributesMap()} instead.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> getAttributes() {
    return getAttributesMap();
  }
  /**
   * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
   */
  @java.lang.Override

  public java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> getAttributesMap() {
    return internalGetAttributes().getMap();
  }
  /**
   * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
   */
  @java.lang.Override

  public io.evitadb.externalApi.grpc.generated.GrpcEvitaValue getAttributesOrDefault(
      java.lang.String key,
      io.evitadb.externalApi.grpc.generated.GrpcEvitaValue defaultValue) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> map =
        internalGetAttributes().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
   */
  @java.lang.Override

  public io.evitadb.externalApi.grpc.generated.GrpcEvitaValue getAttributesOrThrow(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> map =
        internalGetAttributes().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
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
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetAttributes(),
        AttributesDefaultEntryHolder.defaultEntry,
        1);
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (java.util.Map.Entry<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> entry
         : internalGetAttributes().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue>
      attributes__ = AttributesDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, attributes__);
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue other = (io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue) obj;

    if (!internalGetAttributes().equals(
        other.internalGetAttributes())) return false;
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
    if (!internalGetAttributes().getMap().isEmpty()) {
      hash = (37 * hash) + ATTRIBUTES_FIELD_NUMBER;
      hash = (53 * hash) + internalGetAttributes().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue prototype) {
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
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue)
      io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValueOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalizedAttributeMutationValue_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetAttributes();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetMutableAttributes();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalizedAttributeMutationValue_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue.class, io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue.newBuilder()
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
      internalGetMutableAttributes().clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcReferenceMutations.internal_static_io_evitadb_externalApi_grpc_generated_GrpcLocalizedAttributeMutationValue_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue build() {
      io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue result = new io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue(this);
      int from_bitField0_ = bitField0_;
      result.attributes_ = internalGetAttributes();
      result.attributes_.makeImmutable();
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue.getDefaultInstance()) return this;
      internalGetMutableAttributes().mergeFrom(
          other.internalGetAttributes());
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
      io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.MapField<
        java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> attributes_;
    private com.google.protobuf.MapField<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue>
    internalGetAttributes() {
      if (attributes_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            AttributesDefaultEntryHolder.defaultEntry);
      }
      return attributes_;
    }
    private com.google.protobuf.MapField<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue>
    internalGetMutableAttributes() {
      onChanged();;
      if (attributes_ == null) {
        attributes_ = com.google.protobuf.MapField.newMapField(
            AttributesDefaultEntryHolder.defaultEntry);
      }
      if (!attributes_.isMutable()) {
        attributes_ = attributes_.copy();
      }
      return attributes_;
    }

    public int getAttributesCount() {
      return internalGetAttributes().getMap().size();
    }
    /**
     * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
     */

    @java.lang.Override
    public boolean containsAttributes(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      return internalGetAttributes().getMap().containsKey(key);
    }
    /**
     * Use {@link #getAttributesMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> getAttributes() {
      return getAttributesMap();
    }
    /**
     * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> getAttributesMap() {
      return internalGetAttributes().getMap();
    }
    /**
     * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
     */
    @java.lang.Override

    public io.evitadb.externalApi.grpc.generated.GrpcEvitaValue getAttributesOrDefault(
        java.lang.String key,
        io.evitadb.externalApi.grpc.generated.GrpcEvitaValue defaultValue) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> map =
          internalGetAttributes().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
     */
    @java.lang.Override

    public io.evitadb.externalApi.grpc.generated.GrpcEvitaValue getAttributesOrThrow(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> map =
          internalGetAttributes().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearAttributes() {
      internalGetMutableAttributes().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
     */

    public Builder removeAttributes(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      internalGetMutableAttributes().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue>
    getMutableAttributes() {
      return internalGetMutableAttributes().getMutableMap();
    }
    /**
     * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
     */
    public Builder putAttributes(
        java.lang.String key,
        io.evitadb.externalApi.grpc.generated.GrpcEvitaValue value) {
      if (key == null) { throw new NullPointerException("map key"); }
      if (value == null) {
  throw new NullPointerException("map value");
}

      internalGetMutableAttributes().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, .io.evitadb.externalApi.grpc.generated.GrpcEvitaValue&gt; attributes = 1;</code>
     */

    public Builder putAllAttributes(
        java.util.Map<java.lang.String, io.evitadb.externalApi.grpc.generated.GrpcEvitaValue> values) {
      internalGetMutableAttributes().getMutableMap()
          .putAll(values);
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue)
  private static final io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcLocalizedAttributeMutationValue>
      PARSER = new com.google.protobuf.AbstractParser<GrpcLocalizedAttributeMutationValue>() {
    @java.lang.Override
    public GrpcLocalizedAttributeMutationValue parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcLocalizedAttributeMutationValue(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcLocalizedAttributeMutationValue> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcLocalizedAttributeMutationValue> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcLocalizedAttributeMutationValue getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

