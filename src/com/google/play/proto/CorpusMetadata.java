// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: proto/Unsorted.proto
package com.google.play.proto;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoField;

import static com.squareup.wire.Message.Datatype.INT32;
import static com.squareup.wire.Message.Datatype.STRING;

public final class CorpusMetadata extends Message {

  public static final Integer DEFAULT_BACKEND = 0;
  public static final String DEFAULT_NAME = "";
  public static final String DEFAULT_LANDINGURL = "";
  public static final String DEFAULT_LIBRARYNAME = "";

  @ProtoField(tag = 1, type = INT32)
  public final Integer backend;

  @ProtoField(tag = 2, type = STRING)
  public final String name;

  @ProtoField(tag = 3, type = STRING)
  public final String landingUrl;

  @ProtoField(tag = 4, type = STRING)
  public final String libraryName;

  public CorpusMetadata(Integer backend, String name, String landingUrl, String libraryName) {
    this.backend = backend;
    this.name = name;
    this.landingUrl = landingUrl;
    this.libraryName = libraryName;
  }

  private CorpusMetadata(Builder builder) {
    this(builder.backend, builder.name, builder.landingUrl, builder.libraryName);
    setBuilder(builder);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof CorpusMetadata)) return false;
    CorpusMetadata o = (CorpusMetadata) other;
    return equals(backend, o.backend)
        && equals(name, o.name)
        && equals(landingUrl, o.landingUrl)
        && equals(libraryName, o.libraryName);
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = backend != null ? backend.hashCode() : 0;
      result = result * 37 + (name != null ? name.hashCode() : 0);
      result = result * 37 + (landingUrl != null ? landingUrl.hashCode() : 0);
      result = result * 37 + (libraryName != null ? libraryName.hashCode() : 0);
      hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<CorpusMetadata> {

    public Integer backend;
    public String name;
    public String landingUrl;
    public String libraryName;

    public Builder() {
    }

    public Builder(CorpusMetadata message) {
      super(message);
      if (message == null) return;
      this.backend = message.backend;
      this.name = message.name;
      this.landingUrl = message.landingUrl;
      this.libraryName = message.libraryName;
    }

    public Builder backend(Integer backend) {
      this.backend = backend;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder landingUrl(String landingUrl) {
      this.landingUrl = landingUrl;
      return this;
    }

    public Builder libraryName(String libraryName) {
      this.libraryName = libraryName;
      return this;
    }

    @Override
    public CorpusMetadata build() {
      return new CorpusMetadata(this);
    }
  }
}
