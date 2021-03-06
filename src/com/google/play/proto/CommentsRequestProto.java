// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Comment.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.BOOL;
import static com.squareup.wire.Message.Datatype.INT64;
import static com.squareup.wire.Message.Datatype.STRING;

public final class CommentsRequestProto extends Message {

  public static final String DEFAULT_ASSETID = "";
  public static final Long DEFAULT_STARTINDEX = 0L;
  public static final Long DEFAULT_NUMENTRIES = 0L;
  public static final Boolean DEFAULT_SHOULDRETURNSELFCOMMENT = false;
  public static final String DEFAULT_ASSETREFERRER = "";

  @ProtoField(tag = 1, type = STRING)
  public final String assetId;

  @ProtoField(tag = 2, type = INT64)
  public final Long startIndex;

  @ProtoField(tag = 3, type = INT64)
  public final Long numEntries;

  @ProtoField(tag = 4, type = BOOL)
  public final Boolean shouldReturnSelfComment;

  @ProtoField(tag = 5, type = STRING)
  public final String assetReferrer;

  public CommentsRequestProto(String assetId, Long startIndex, Long numEntries, Boolean shouldReturnSelfComment, String assetReferrer) {
    this.assetId = assetId;
    this.startIndex = startIndex;
    this.numEntries = numEntries;
    this.shouldReturnSelfComment = shouldReturnSelfComment;
    this.assetReferrer = assetReferrer;
  }

  private CommentsRequestProto(Builder builder) {
    this(builder.assetId, builder.startIndex, builder.numEntries, builder.shouldReturnSelfComment, builder.assetReferrer);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof CommentsRequestProto)) return false;
    CommentsRequestProto o = (CommentsRequestProto) other;
    return equals(assetId, o.assetId)
        && equals(startIndex, o.startIndex)
        && equals(numEntries, o.numEntries)
        && equals(shouldReturnSelfComment, o.shouldReturnSelfComment)
        && equals(assetReferrer, o.assetReferrer);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = assetId != null ? assetId.hashCode() : 0;
      result = result * 37 + (startIndex != null ? startIndex.hashCode() : 0);
      result = result * 37 + (numEntries != null ? numEntries.hashCode() : 0);
      result = result * 37 + (shouldReturnSelfComment != null ? shouldReturnSelfComment.hashCode() : 0);
      result = result * 37 + (assetReferrer != null ? assetReferrer.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<CommentsRequestProto> {

    public String assetId;
    public Long startIndex;
    public Long numEntries;
    public Boolean shouldReturnSelfComment;
    public String assetReferrer;

    public Builder() {
    }

    public Builder(CommentsRequestProto message) {
      super(message);
      if (message == null) return;
      this.assetId = message.assetId;
      this.startIndex = message.startIndex;
      this.numEntries = message.numEntries;
      this.shouldReturnSelfComment = message.shouldReturnSelfComment;
      this.assetReferrer = message.assetReferrer;
    }

    public Builder assetId(String assetId) {
      this.assetId = assetId;
      return this;
    }

    public Builder startIndex(Long startIndex) {
      this.startIndex = startIndex;
      return this;
    }

    public Builder numEntries(Long numEntries) {
      this.numEntries = numEntries;
      return this;
    }

    public Builder shouldReturnSelfComment(Boolean shouldReturnSelfComment) {
      this.shouldReturnSelfComment = shouldReturnSelfComment;
      return this;
    }

    public Builder assetReferrer(String assetReferrer) {
      this.assetReferrer = assetReferrer;
      return this;
    }

    @Override
    public CommentsRequestProto build() {
      return new CommentsRequestProto(this);
    }
  }
}
