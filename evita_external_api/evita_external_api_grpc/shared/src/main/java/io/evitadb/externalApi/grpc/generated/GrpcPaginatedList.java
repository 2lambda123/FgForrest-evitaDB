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
// source: GrpcEvitaSession.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcPaginatedList}
 */
public final class GrpcPaginatedList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcPaginatedList)
    GrpcPaginatedListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcPaginatedList.newBuilder() to construct.
  private GrpcPaginatedList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcPaginatedList() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcPaginatedList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcPaginatedList(
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

            pageSize_ = input.readInt32();
            break;
          }
          case 16: {

            pageNumber_ = input.readInt32();
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
    return io.evitadb.externalApi.grpc.generated.GrpcEvitaSession.internal_static_io_evitadb_externalApi_grpc_generated_GrpcPaginatedList_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcEvitaSession.internal_static_io_evitadb_externalApi_grpc_generated_GrpcPaginatedList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcPaginatedList.class, io.evitadb.externalApi.grpc.generated.GrpcPaginatedList.Builder.class);
  }

  public static final int PAGESIZE_FIELD_NUMBER = 1;
  private int pageSize_;
  /**
   * <code>int32 pageSize = 1;</code>
   * @return The pageSize.
   */
  @java.lang.Override
  public int getPageSize() {
    return pageSize_;
  }

  public static final int PAGENUMBER_FIELD_NUMBER = 2;
  private int pageNumber_;
  /**
   * <code>int32 pageNumber = 2;</code>
   * @return The pageNumber.
   */
  @java.lang.Override
  public int getPageNumber() {
    return pageNumber_;
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
    if (pageSize_ != 0) {
      output.writeInt32(1, pageSize_);
    }
    if (pageNumber_ != 0) {
      output.writeInt32(2, pageNumber_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (pageSize_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, pageSize_);
    }
    if (pageNumber_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, pageNumber_);
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcPaginatedList)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcPaginatedList other = (io.evitadb.externalApi.grpc.generated.GrpcPaginatedList) obj;

    if (getPageSize()
        != other.getPageSize()) return false;
    if (getPageNumber()
        != other.getPageNumber()) return false;
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
    hash = (37 * hash) + PAGESIZE_FIELD_NUMBER;
    hash = (53 * hash) + getPageSize();
    hash = (37 * hash) + PAGENUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getPageNumber();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcPaginatedList prototype) {
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
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcPaginatedList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcPaginatedList)
      io.evitadb.externalApi.grpc.generated.GrpcPaginatedListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSession.internal_static_io_evitadb_externalApi_grpc_generated_GrpcPaginatedList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSession.internal_static_io_evitadb_externalApi_grpc_generated_GrpcPaginatedList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcPaginatedList.class, io.evitadb.externalApi.grpc.generated.GrpcPaginatedList.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcPaginatedList.newBuilder()
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
      pageSize_ = 0;

      pageNumber_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSession.internal_static_io_evitadb_externalApi_grpc_generated_GrpcPaginatedList_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcPaginatedList getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcPaginatedList.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcPaginatedList build() {
      io.evitadb.externalApi.grpc.generated.GrpcPaginatedList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcPaginatedList buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcPaginatedList result = new io.evitadb.externalApi.grpc.generated.GrpcPaginatedList(this);
      result.pageSize_ = pageSize_;
      result.pageNumber_ = pageNumber_;
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcPaginatedList) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcPaginatedList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcPaginatedList other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcPaginatedList.getDefaultInstance()) return this;
      if (other.getPageSize() != 0) {
        setPageSize(other.getPageSize());
      }
      if (other.getPageNumber() != 0) {
        setPageNumber(other.getPageNumber());
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
      io.evitadb.externalApi.grpc.generated.GrpcPaginatedList parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcPaginatedList) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int pageSize_ ;
    /**
     * <code>int32 pageSize = 1;</code>
     * @return The pageSize.
     */
    @java.lang.Override
    public int getPageSize() {
      return pageSize_;
    }
    /**
     * <code>int32 pageSize = 1;</code>
     * @param value The pageSize to set.
     * @return This builder for chaining.
     */
    public Builder setPageSize(int value) {
      
      pageSize_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 pageSize = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPageSize() {
      
      pageSize_ = 0;
      onChanged();
      return this;
    }

    private int pageNumber_ ;
    /**
     * <code>int32 pageNumber = 2;</code>
     * @return The pageNumber.
     */
    @java.lang.Override
    public int getPageNumber() {
      return pageNumber_;
    }
    /**
     * <code>int32 pageNumber = 2;</code>
     * @param value The pageNumber to set.
     * @return This builder for chaining.
     */
    public Builder setPageNumber(int value) {
      
      pageNumber_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 pageNumber = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPageNumber() {
      
      pageNumber_ = 0;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcPaginatedList)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcPaginatedList)
  private static final io.evitadb.externalApi.grpc.generated.GrpcPaginatedList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcPaginatedList();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcPaginatedList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcPaginatedList>
      PARSER = new com.google.protobuf.AbstractParser<GrpcPaginatedList>() {
    @java.lang.Override
    public GrpcPaginatedList parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcPaginatedList(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcPaginatedList> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcPaginatedList> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcPaginatedList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

