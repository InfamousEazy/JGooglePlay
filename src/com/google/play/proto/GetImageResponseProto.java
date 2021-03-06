// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/BadgeImage.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;
import okio.ByteString;

import static com.squareup.wire.Message.Datatype.BYTES;
import static com.squareup.wire.Message.Datatype.INT32;

public final class GetImageResponseProto extends Message {

  public static final ByteString DEFAULT_IMAGEDATA = ByteString.EMPTY;
  public static final Integer DEFAULT_IMAGEDENSITY = 0;

  @ProtoField(tag = 1, type = BYTES)
  public final ByteString imageData;

  @ProtoField(tag = 2, type = INT32)
  public final Integer imageDensity;

  public GetImageResponseProto(ByteString imageData, Integer imageDensity) {
    this.imageData = imageData;
    this.imageDensity = imageDensity;
  }

  private GetImageResponseProto(Builder builder) {
    this(builder.imageData, builder.imageDensity);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof GetImageResponseProto)) return false;
    GetImageResponseProto o = (GetImageResponseProto) other;
    return equals(imageData, o.imageData)
        && equals(imageDensity, o.imageDensity);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = imageData != null ? imageData.hashCode() : 0;
      result = result * 37 + (imageDensity != null ? imageDensity.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<GetImageResponseProto> {

    public ByteString imageData;
    public Integer imageDensity;

    public Builder() {
    }

    public Builder(GetImageResponseProto message) {
      super(message);
      if (message == null) return;
      this.imageData = message.imageData;
      this.imageDensity = message.imageDensity;
    }

    public Builder imageData(ByteString imageData) {
      this.imageData = imageData;
      return this;
    }

    public Builder imageDensity(Integer imageDensity) {
      this.imageDensity = imageDensity;
      return this;
    }

    @Override
    public GetImageResponseProto build() {
      return new GetImageResponseProto(this);
    }
  }
}
