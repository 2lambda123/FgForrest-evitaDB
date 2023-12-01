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
 * Response to Query request.
 * </pre>
 *
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcQueryResponse}
 */
public final class GrpcQueryResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcQueryResponse)
    GrpcQueryResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcQueryResponse.newBuilder() to construct.
  private GrpcQueryResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcQueryResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcQueryResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcQueryResponse(
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
            io.evitadb.externalApi.grpc.generated.GrpcDataChunk.Builder subBuilder = null;
            if (recordPage_ != null) {
              subBuilder = recordPage_.toBuilder();
            }
            recordPage_ = input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcDataChunk.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(recordPage_);
              recordPage_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            io.evitadb.externalApi.grpc.generated.GrpcExtraResults.Builder subBuilder = null;
            if (extraResults_ != null) {
              subBuilder = extraResults_.toBuilder();
            }
            extraResults_ = input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcExtraResults.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(extraResults_);
              extraResults_ = subBuilder.buildPartial();
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
    return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcQueryResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcQueryResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcQueryResponse.class, io.evitadb.externalApi.grpc.generated.GrpcQueryResponse.Builder.class);
  }

  public static final int RECORDPAGE_FIELD_NUMBER = 1;
  private io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage_;
  /**
   * <pre>
   * The fetched record page with entities.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
   * @return Whether the recordPage field is set.
   */
  @java.lang.Override
  public boolean hasRecordPage() {
    return recordPage_ != null;
  }
  /**
   * <pre>
   * The fetched record page with entities.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
   * @return The recordPage.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcDataChunk getRecordPage() {
    return recordPage_ == null ? io.evitadb.externalApi.grpc.generated.GrpcDataChunk.getDefaultInstance() : recordPage_;
  }
  /**
   * <pre>
   * The fetched record page with entities.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcDataChunkOrBuilder getRecordPageOrBuilder() {
    return getRecordPage();
  }

  public static final int EXTRARESULTS_FIELD_NUMBER = 2;
  private io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults_;
  /**
   * <pre>
   * The computed extra results.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
   * @return Whether the extraResults field is set.
   */
  @java.lang.Override
  public boolean hasExtraResults() {
    return extraResults_ != null;
  }
  /**
   * <pre>
   * The computed extra results.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
   * @return The extraResults.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcExtraResults getExtraResults() {
    return extraResults_ == null ? io.evitadb.externalApi.grpc.generated.GrpcExtraResults.getDefaultInstance() : extraResults_;
  }
  /**
   * <pre>
   * The computed extra results.
   * </pre>
   *
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcExtraResultsOrBuilder getExtraResultsOrBuilder() {
    return getExtraResults();
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
    if (recordPage_ != null) {
      output.writeMessage(1, getRecordPage());
    }
    if (extraResults_ != null) {
      output.writeMessage(2, getExtraResults());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (recordPage_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getRecordPage());
    }
    if (extraResults_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getExtraResults());
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcQueryResponse)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcQueryResponse other = (io.evitadb.externalApi.grpc.generated.GrpcQueryResponse) obj;

    if (hasRecordPage() != other.hasRecordPage()) return false;
    if (hasRecordPage()) {
      if (!getRecordPage()
          .equals(other.getRecordPage())) return false;
    }
    if (hasExtraResults() != other.hasExtraResults()) return false;
    if (hasExtraResults()) {
      if (!getExtraResults()
          .equals(other.getExtraResults())) return false;
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
    if (hasRecordPage()) {
      hash = (37 * hash) + RECORDPAGE_FIELD_NUMBER;
      hash = (53 * hash) + getRecordPage().hashCode();
    }
    if (hasExtraResults()) {
      hash = (37 * hash) + EXTRARESULTS_FIELD_NUMBER;
      hash = (53 * hash) + getExtraResults().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcQueryResponse prototype) {
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
   * Response to Query request.
   * </pre>
   *
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcQueryResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcQueryResponse)
      io.evitadb.externalApi.grpc.generated.GrpcQueryResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcQueryResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcQueryResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcQueryResponse.class, io.evitadb.externalApi.grpc.generated.GrpcQueryResponse.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcQueryResponse.newBuilder()
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
      if (recordPageBuilder_ == null) {
        recordPage_ = null;
      } else {
        recordPage_ = null;
        recordPageBuilder_ = null;
      }
      if (extraResultsBuilder_ == null) {
        extraResults_ = null;
      } else {
        extraResults_ = null;
        extraResultsBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcEvitaSessionAPI.internal_static_io_evitadb_externalApi_grpc_generated_GrpcQueryResponse_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcQueryResponse getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcQueryResponse.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcQueryResponse build() {
      io.evitadb.externalApi.grpc.generated.GrpcQueryResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcQueryResponse buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcQueryResponse result = new io.evitadb.externalApi.grpc.generated.GrpcQueryResponse(this);
      if (recordPageBuilder_ == null) {
        result.recordPage_ = recordPage_;
      } else {
        result.recordPage_ = recordPageBuilder_.build();
      }
      if (extraResultsBuilder_ == null) {
        result.extraResults_ = extraResults_;
      } else {
        result.extraResults_ = extraResultsBuilder_.build();
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcQueryResponse) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcQueryResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcQueryResponse other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcQueryResponse.getDefaultInstance()) return this;
      if (other.hasRecordPage()) {
        mergeRecordPage(other.getRecordPage());
      }
      if (other.hasExtraResults()) {
        mergeExtraResults(other.getExtraResults());
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
      io.evitadb.externalApi.grpc.generated.GrpcQueryResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcQueryResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcDataChunk, io.evitadb.externalApi.grpc.generated.GrpcDataChunk.Builder, io.evitadb.externalApi.grpc.generated.GrpcDataChunkOrBuilder> recordPageBuilder_;
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     * @return Whether the recordPage field is set.
     */
    public boolean hasRecordPage() {
      return recordPageBuilder_ != null || recordPage_ != null;
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     * @return The recordPage.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcDataChunk getRecordPage() {
      if (recordPageBuilder_ == null) {
        return recordPage_ == null ? io.evitadb.externalApi.grpc.generated.GrpcDataChunk.getDefaultInstance() : recordPage_;
      } else {
        return recordPageBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     */
    public Builder setRecordPage(io.evitadb.externalApi.grpc.generated.GrpcDataChunk value) {
      if (recordPageBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        recordPage_ = value;
        onChanged();
      } else {
        recordPageBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     */
    public Builder setRecordPage(
        io.evitadb.externalApi.grpc.generated.GrpcDataChunk.Builder builderForValue) {
      if (recordPageBuilder_ == null) {
        recordPage_ = builderForValue.build();
        onChanged();
      } else {
        recordPageBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     */
    public Builder mergeRecordPage(io.evitadb.externalApi.grpc.generated.GrpcDataChunk value) {
      if (recordPageBuilder_ == null) {
        if (recordPage_ != null) {
          recordPage_ =
            io.evitadb.externalApi.grpc.generated.GrpcDataChunk.newBuilder(recordPage_).mergeFrom(value).buildPartial();
        } else {
          recordPage_ = value;
        }
        onChanged();
      } else {
        recordPageBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     */
    public Builder clearRecordPage() {
      if (recordPageBuilder_ == null) {
        recordPage_ = null;
        onChanged();
      } else {
        recordPage_ = null;
        recordPageBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcDataChunk.Builder getRecordPageBuilder() {

      onChanged();
      return getRecordPageFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcDataChunkOrBuilder getRecordPageOrBuilder() {
      if (recordPageBuilder_ != null) {
        return recordPageBuilder_.getMessageOrBuilder();
      } else {
        return recordPage_ == null ?
            io.evitadb.externalApi.grpc.generated.GrpcDataChunk.getDefaultInstance() : recordPage_;
      }
    }
    /**
     * <pre>
     * The fetched record page with entities.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcDataChunk recordPage = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcDataChunk, io.evitadb.externalApi.grpc.generated.GrpcDataChunk.Builder, io.evitadb.externalApi.grpc.generated.GrpcDataChunkOrBuilder>
        getRecordPageFieldBuilder() {
      if (recordPageBuilder_ == null) {
        recordPageBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcDataChunk, io.evitadb.externalApi.grpc.generated.GrpcDataChunk.Builder, io.evitadb.externalApi.grpc.generated.GrpcDataChunkOrBuilder>(
                getRecordPage(),
                getParentForChildren(),
                isClean());
        recordPage_ = null;
      }
      return recordPageBuilder_;
    }

    private io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcExtraResults, io.evitadb.externalApi.grpc.generated.GrpcExtraResults.Builder, io.evitadb.externalApi.grpc.generated.GrpcExtraResultsOrBuilder> extraResultsBuilder_;
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     * @return Whether the extraResults field is set.
     */
    public boolean hasExtraResults() {
      return extraResultsBuilder_ != null || extraResults_ != null;
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     * @return The extraResults.
     */
    public io.evitadb.externalApi.grpc.generated.GrpcExtraResults getExtraResults() {
      if (extraResultsBuilder_ == null) {
        return extraResults_ == null ? io.evitadb.externalApi.grpc.generated.GrpcExtraResults.getDefaultInstance() : extraResults_;
      } else {
        return extraResultsBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     */
    public Builder setExtraResults(io.evitadb.externalApi.grpc.generated.GrpcExtraResults value) {
      if (extraResultsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        extraResults_ = value;
        onChanged();
      } else {
        extraResultsBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     */
    public Builder setExtraResults(
        io.evitadb.externalApi.grpc.generated.GrpcExtraResults.Builder builderForValue) {
      if (extraResultsBuilder_ == null) {
        extraResults_ = builderForValue.build();
        onChanged();
      } else {
        extraResultsBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     */
    public Builder mergeExtraResults(io.evitadb.externalApi.grpc.generated.GrpcExtraResults value) {
      if (extraResultsBuilder_ == null) {
        if (extraResults_ != null) {
          extraResults_ =
            io.evitadb.externalApi.grpc.generated.GrpcExtraResults.newBuilder(extraResults_).mergeFrom(value).buildPartial();
        } else {
          extraResults_ = value;
        }
        onChanged();
      } else {
        extraResultsBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     */
    public Builder clearExtraResults() {
      if (extraResultsBuilder_ == null) {
        extraResults_ = null;
        onChanged();
      } else {
        extraResults_ = null;
        extraResultsBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcExtraResults.Builder getExtraResultsBuilder() {

      onChanged();
      return getExtraResultsFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcExtraResultsOrBuilder getExtraResultsOrBuilder() {
      if (extraResultsBuilder_ != null) {
        return extraResultsBuilder_.getMessageOrBuilder();
      } else {
        return extraResults_ == null ?
            io.evitadb.externalApi.grpc.generated.GrpcExtraResults.getDefaultInstance() : extraResults_;
      }
    }
    /**
     * <pre>
     * The computed extra results.
     * </pre>
     *
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcExtraResults extraResults = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcExtraResults, io.evitadb.externalApi.grpc.generated.GrpcExtraResults.Builder, io.evitadb.externalApi.grpc.generated.GrpcExtraResultsOrBuilder>
        getExtraResultsFieldBuilder() {
      if (extraResultsBuilder_ == null) {
        extraResultsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcExtraResults, io.evitadb.externalApi.grpc.generated.GrpcExtraResults.Builder, io.evitadb.externalApi.grpc.generated.GrpcExtraResultsOrBuilder>(
                getExtraResults(),
                getParentForChildren(),
                isClean());
        extraResults_ = null;
      }
      return extraResultsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcQueryResponse)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcQueryResponse)
  private static final io.evitadb.externalApi.grpc.generated.GrpcQueryResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcQueryResponse();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcQueryResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcQueryResponse>
      PARSER = new com.google.protobuf.AbstractParser<GrpcQueryResponse>() {
    @java.lang.Override
    public GrpcQueryResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcQueryResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcQueryResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcQueryResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcQueryResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

