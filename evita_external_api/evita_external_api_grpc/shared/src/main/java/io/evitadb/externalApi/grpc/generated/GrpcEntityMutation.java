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
// source: GrpcEntityMutation.proto

package io.evitadb.externalApi.grpc.generated;

/**
 * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcEntityMutation}
 */
public final class GrpcEntityMutation extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:io.evitadb.externalApi.grpc.generated.GrpcEntityMutation)
    GrpcEntityMutationOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GrpcEntityMutation.newBuilder() to construct.
  private GrpcEntityMutation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcEntityMutation() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GrpcEntityMutation();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GrpcEntityMutation(
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
            io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.Builder subBuilder = null;
            if (mutationCase_ == 1) {
              subBuilder = ((io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_).toBuilder();
            }
            mutation_ =
                input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_);
              mutation_ = subBuilder.buildPartial();
            }
            mutationCase_ = 1;
            break;
          }
          case 18: {
            io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.Builder subBuilder = null;
            if (mutationCase_ == 2) {
              subBuilder = ((io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_).toBuilder();
            }
            mutation_ =
                input.readMessage(io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_);
              mutation_ = subBuilder.buildPartial();
            }
            mutationCase_ = 2;
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
    return io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOuterClass.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityMutation_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOuterClass.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityMutation_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.evitadb.externalApi.grpc.generated.GrpcEntityMutation.class, io.evitadb.externalApi.grpc.generated.GrpcEntityMutation.Builder.class);
  }

  private int mutationCase_ = 0;
  private java.lang.Object mutation_;
  public enum MutationCase
      implements com.google.protobuf.Internal.EnumLite,
          com.google.protobuf.AbstractMessage.InternalOneOfEnum {
    ENTITYUPSERTMUTATION(1),
    ENTITYREMOVEMUTATION(2),
    MUTATION_NOT_SET(0);
    private final int value;
    private MutationCase(int value) {
      this.value = value;
    }
    /**
     * @param value The number of the enum to look for.
     * @return The enum associated with the given number.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static MutationCase valueOf(int value) {
      return forNumber(value);
    }

    public static MutationCase forNumber(int value) {
      switch (value) {
        case 1: return ENTITYUPSERTMUTATION;
        case 2: return ENTITYREMOVEMUTATION;
        case 0: return MUTATION_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public MutationCase
  getMutationCase() {
    return MutationCase.forNumber(
        mutationCase_);
  }

  public static final int ENTITYUPSERTMUTATION_FIELD_NUMBER = 1;
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
   * @return Whether the entityUpsertMutation field is set.
   */
  @java.lang.Override
  public boolean hasEntityUpsertMutation() {
    return mutationCase_ == 1;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
   * @return The entityUpsertMutation.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation getEntityUpsertMutation() {
    if (mutationCase_ == 1) {
       return (io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_;
    }
    return io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.getDefaultInstance();
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutationOrBuilder getEntityUpsertMutationOrBuilder() {
    if (mutationCase_ == 1) {
       return (io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_;
    }
    return io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.getDefaultInstance();
  }

  public static final int ENTITYREMOVEMUTATION_FIELD_NUMBER = 2;
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
   * @return Whether the entityRemoveMutation field is set.
   */
  @java.lang.Override
  public boolean hasEntityRemoveMutation() {
    return mutationCase_ == 2;
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
   * @return The entityRemoveMutation.
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation getEntityRemoveMutation() {
    if (mutationCase_ == 2) {
       return (io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_;
    }
    return io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.getDefaultInstance();
  }
  /**
   * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
   */
  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutationOrBuilder getEntityRemoveMutationOrBuilder() {
    if (mutationCase_ == 2) {
       return (io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_;
    }
    return io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.getDefaultInstance();
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
    if (mutationCase_ == 1) {
      output.writeMessage(1, (io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_);
    }
    if (mutationCase_ == 2) {
      output.writeMessage(2, (io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (mutationCase_ == 1) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, (io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_);
    }
    if (mutationCase_ == 2) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, (io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_);
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
    if (!(obj instanceof io.evitadb.externalApi.grpc.generated.GrpcEntityMutation)) {
      return super.equals(obj);
    }
    io.evitadb.externalApi.grpc.generated.GrpcEntityMutation other = (io.evitadb.externalApi.grpc.generated.GrpcEntityMutation) obj;

    if (!getMutationCase().equals(other.getMutationCase())) return false;
    switch (mutationCase_) {
      case 1:
        if (!getEntityUpsertMutation()
            .equals(other.getEntityUpsertMutation())) return false;
        break;
      case 2:
        if (!getEntityRemoveMutation()
            .equals(other.getEntityRemoveMutation())) return false;
        break;
      case 0:
      default:
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
    switch (mutationCase_) {
      case 1:
        hash = (37 * hash) + ENTITYUPSERTMUTATION_FIELD_NUMBER;
        hash = (53 * hash) + getEntityUpsertMutation().hashCode();
        break;
      case 2:
        hash = (37 * hash) + ENTITYREMOVEMUTATION_FIELD_NUMBER;
        hash = (53 * hash) + getEntityRemoveMutation().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parseFrom(
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
  public static Builder newBuilder(io.evitadb.externalApi.grpc.generated.GrpcEntityMutation prototype) {
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
   * Protobuf type {@code io.evitadb.externalApi.grpc.generated.GrpcEntityMutation}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:io.evitadb.externalApi.grpc.generated.GrpcEntityMutation)
      io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOuterClass.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityMutation_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOuterClass.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityMutation_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.evitadb.externalApi.grpc.generated.GrpcEntityMutation.class, io.evitadb.externalApi.grpc.generated.GrpcEntityMutation.Builder.class);
    }

    // Construct using io.evitadb.externalApi.grpc.generated.GrpcEntityMutation.newBuilder()
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
      mutationCase_ = 0;
      mutation_ = null;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntityMutationOuterClass.internal_static_io_evitadb_externalApi_grpc_generated_GrpcEntityMutation_descriptor;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityMutation getDefaultInstanceForType() {
      return io.evitadb.externalApi.grpc.generated.GrpcEntityMutation.getDefaultInstance();
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityMutation build() {
      io.evitadb.externalApi.grpc.generated.GrpcEntityMutation result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityMutation buildPartial() {
      io.evitadb.externalApi.grpc.generated.GrpcEntityMutation result = new io.evitadb.externalApi.grpc.generated.GrpcEntityMutation(this);
      if (mutationCase_ == 1) {
        if (entityUpsertMutationBuilder_ == null) {
          result.mutation_ = mutation_;
        } else {
          result.mutation_ = entityUpsertMutationBuilder_.build();
        }
      }
      if (mutationCase_ == 2) {
        if (entityRemoveMutationBuilder_ == null) {
          result.mutation_ = mutation_;
        } else {
          result.mutation_ = entityRemoveMutationBuilder_.build();
        }
      }
      result.mutationCase_ = mutationCase_;
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
      if (other instanceof io.evitadb.externalApi.grpc.generated.GrpcEntityMutation) {
        return mergeFrom((io.evitadb.externalApi.grpc.generated.GrpcEntityMutation)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.evitadb.externalApi.grpc.generated.GrpcEntityMutation other) {
      if (other == io.evitadb.externalApi.grpc.generated.GrpcEntityMutation.getDefaultInstance()) return this;
      switch (other.getMutationCase()) {
        case ENTITYUPSERTMUTATION: {
          mergeEntityUpsertMutation(other.getEntityUpsertMutation());
          break;
        }
        case ENTITYREMOVEMUTATION: {
          mergeEntityRemoveMutation(other.getEntityRemoveMutation());
          break;
        }
        case MUTATION_NOT_SET: {
          break;
        }
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
      io.evitadb.externalApi.grpc.generated.GrpcEntityMutation parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.evitadb.externalApi.grpc.generated.GrpcEntityMutation) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int mutationCase_ = 0;
    private java.lang.Object mutation_;
    public MutationCase
        getMutationCase() {
      return MutationCase.forNumber(
          mutationCase_);
    }

    public Builder clearMutation() {
      mutationCase_ = 0;
      mutation_ = null;
      onChanged();
      return this;
    }


    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation, io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutationOrBuilder> entityUpsertMutationBuilder_;
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     * @return Whether the entityUpsertMutation field is set.
     */
    @java.lang.Override
    public boolean hasEntityUpsertMutation() {
      return mutationCase_ == 1;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     * @return The entityUpsertMutation.
     */
    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation getEntityUpsertMutation() {
      if (entityUpsertMutationBuilder_ == null) {
        if (mutationCase_ == 1) {
          return (io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_;
        }
        return io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.getDefaultInstance();
      } else {
        if (mutationCase_ == 1) {
          return entityUpsertMutationBuilder_.getMessage();
        }
        return io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.getDefaultInstance();
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     */
    public Builder setEntityUpsertMutation(io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation value) {
      if (entityUpsertMutationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        mutation_ = value;
        onChanged();
      } else {
        entityUpsertMutationBuilder_.setMessage(value);
      }
      mutationCase_ = 1;
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     */
    public Builder setEntityUpsertMutation(
        io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.Builder builderForValue) {
      if (entityUpsertMutationBuilder_ == null) {
        mutation_ = builderForValue.build();
        onChanged();
      } else {
        entityUpsertMutationBuilder_.setMessage(builderForValue.build());
      }
      mutationCase_ = 1;
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     */
    public Builder mergeEntityUpsertMutation(io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation value) {
      if (entityUpsertMutationBuilder_ == null) {
        if (mutationCase_ == 1 &&
            mutation_ != io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.getDefaultInstance()) {
          mutation_ = io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.newBuilder((io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_)
              .mergeFrom(value).buildPartial();
        } else {
          mutation_ = value;
        }
        onChanged();
      } else {
        if (mutationCase_ == 1) {
          entityUpsertMutationBuilder_.mergeFrom(value);
        }
        entityUpsertMutationBuilder_.setMessage(value);
      }
      mutationCase_ = 1;
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     */
    public Builder clearEntityUpsertMutation() {
      if (entityUpsertMutationBuilder_ == null) {
        if (mutationCase_ == 1) {
          mutationCase_ = 0;
          mutation_ = null;
          onChanged();
        }
      } else {
        if (mutationCase_ == 1) {
          mutationCase_ = 0;
          mutation_ = null;
        }
        entityUpsertMutationBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.Builder getEntityUpsertMutationBuilder() {
      return getEntityUpsertMutationFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     */
    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutationOrBuilder getEntityUpsertMutationOrBuilder() {
      if ((mutationCase_ == 1) && (entityUpsertMutationBuilder_ != null)) {
        return entityUpsertMutationBuilder_.getMessageOrBuilder();
      } else {
        if (mutationCase_ == 1) {
          return (io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_;
        }
        return io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.getDefaultInstance();
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation entityUpsertMutation = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation, io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutationOrBuilder>
        getEntityUpsertMutationFieldBuilder() {
      if (entityUpsertMutationBuilder_ == null) {
        if (!(mutationCase_ == 1)) {
          mutation_ = io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.getDefaultInstance();
        }
        entityUpsertMutationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation, io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutationOrBuilder>(
                (io.evitadb.externalApi.grpc.generated.GrpcEntityUpsertMutation) mutation_,
                getParentForChildren(),
                isClean());
        mutation_ = null;
      }
      mutationCase_ = 1;
      onChanged();;
      return entityUpsertMutationBuilder_;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation, io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutationOrBuilder> entityRemoveMutationBuilder_;
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     * @return Whether the entityRemoveMutation field is set.
     */
    @java.lang.Override
    public boolean hasEntityRemoveMutation() {
      return mutationCase_ == 2;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     * @return The entityRemoveMutation.
     */
    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation getEntityRemoveMutation() {
      if (entityRemoveMutationBuilder_ == null) {
        if (mutationCase_ == 2) {
          return (io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_;
        }
        return io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.getDefaultInstance();
      } else {
        if (mutationCase_ == 2) {
          return entityRemoveMutationBuilder_.getMessage();
        }
        return io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.getDefaultInstance();
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     */
    public Builder setEntityRemoveMutation(io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation value) {
      if (entityRemoveMutationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        mutation_ = value;
        onChanged();
      } else {
        entityRemoveMutationBuilder_.setMessage(value);
      }
      mutationCase_ = 2;
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     */
    public Builder setEntityRemoveMutation(
        io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.Builder builderForValue) {
      if (entityRemoveMutationBuilder_ == null) {
        mutation_ = builderForValue.build();
        onChanged();
      } else {
        entityRemoveMutationBuilder_.setMessage(builderForValue.build());
      }
      mutationCase_ = 2;
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     */
    public Builder mergeEntityRemoveMutation(io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation value) {
      if (entityRemoveMutationBuilder_ == null) {
        if (mutationCase_ == 2 &&
            mutation_ != io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.getDefaultInstance()) {
          mutation_ = io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.newBuilder((io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_)
              .mergeFrom(value).buildPartial();
        } else {
          mutation_ = value;
        }
        onChanged();
      } else {
        if (mutationCase_ == 2) {
          entityRemoveMutationBuilder_.mergeFrom(value);
        }
        entityRemoveMutationBuilder_.setMessage(value);
      }
      mutationCase_ = 2;
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     */
    public Builder clearEntityRemoveMutation() {
      if (entityRemoveMutationBuilder_ == null) {
        if (mutationCase_ == 2) {
          mutationCase_ = 0;
          mutation_ = null;
          onChanged();
        }
      } else {
        if (mutationCase_ == 2) {
          mutationCase_ = 0;
          mutation_ = null;
        }
        entityRemoveMutationBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     */
    public io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.Builder getEntityRemoveMutationBuilder() {
      return getEntityRemoveMutationFieldBuilder().getBuilder();
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     */
    @java.lang.Override
    public io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutationOrBuilder getEntityRemoveMutationOrBuilder() {
      if ((mutationCase_ == 2) && (entityRemoveMutationBuilder_ != null)) {
        return entityRemoveMutationBuilder_.getMessageOrBuilder();
      } else {
        if (mutationCase_ == 2) {
          return (io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_;
        }
        return io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.getDefaultInstance();
      }
    }
    /**
     * <code>.io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation entityRemoveMutation = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation, io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutationOrBuilder>
        getEntityRemoveMutationFieldBuilder() {
      if (entityRemoveMutationBuilder_ == null) {
        if (!(mutationCase_ == 2)) {
          mutation_ = io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.getDefaultInstance();
        }
        entityRemoveMutationBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation, io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation.Builder, io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutationOrBuilder>(
                (io.evitadb.externalApi.grpc.generated.GrpcEntityRemoveMutation) mutation_,
                getParentForChildren(),
                isClean());
        mutation_ = null;
      }
      mutationCase_ = 2;
      onChanged();;
      return entityRemoveMutationBuilder_;
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


    // @@protoc_insertion_point(builder_scope:io.evitadb.externalApi.grpc.generated.GrpcEntityMutation)
  }

  // @@protoc_insertion_point(class_scope:io.evitadb.externalApi.grpc.generated.GrpcEntityMutation)
  private static final io.evitadb.externalApi.grpc.generated.GrpcEntityMutation DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.evitadb.externalApi.grpc.generated.GrpcEntityMutation();
  }

  public static io.evitadb.externalApi.grpc.generated.GrpcEntityMutation getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcEntityMutation>
      PARSER = new com.google.protobuf.AbstractParser<GrpcEntityMutation>() {
    @java.lang.Override
    public GrpcEntityMutation parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GrpcEntityMutation(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcEntityMutation> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GrpcEntityMutation> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.evitadb.externalApi.grpc.generated.GrpcEntityMutation getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

