// Block.java
package com.example.demo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;


public final class Block {
    @NotNull
    private HashMap properties = new HashMap();

    @JsonAnyGetter
    @NotNull
    public final HashMap getProperties() {
        return this.properties;
    }

    public final void setProperties(@NotNull HashMap var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.properties = var1;
    }

    @NotNull
    public final hash hashTohash(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        return new hash(string);
    }

    @NotNull
    public final txhash hashTotxhash(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        return new txhash(string);
    }

    @NotNull
    public final address addressTohash(@NotNull String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        return new address(string);
    }
}
// hash.java
@JsonPropertyOrder({"link", "hash"})
@JsonRootName("hash")
final class hash {
    @NotNull
    private final String hash;
    @NotNull
    private final String href;

    @NotNull
    public final String getHash() {
        return this.hash;
    }

    @NotNull
    public final String getHref() {
        return this.href;
    }

    public hash(@NotNull String hash) {
        Intrinsics.checkParameterIsNotNull(hash, "hash");
        this.hash = hash;
        this.href = "?list=Block_item&hash=" + hash;
    }
}

// txhash.java
@JsonPropertyOrder({"link", "hash"})
@JsonRootName("hash")
final class txhash {
    @NotNull
    private final String hash;
    @NotNull
    private final String href;

    @NotNull
    public final String getHash() {
        return this.hash;
    }

    @NotNull
    public final String getHref() {
        return this.href;
    }

    public txhash(@NotNull String hash) {
        Intrinsics.checkParameterIsNotNull(hash, "hash");
        this.hash = hash;
        this.href = "?list=Transaction_item&hash=" + hash;
    }
}

// address.java
@JsonPropertyOrder({"link", "hash"})
@JsonRootName("hash")
final class address {
    @NotNull
    private final String hash;
    @NotNull
    private final String href;

    @NotNull
    public final String getHash() {
        return this.hash;
    }

    @NotNull
    public final String getHref() {
        return this.href;
    }

    public address(@NotNull String hash) {
        Intrinsics.checkParameterIsNotNull(hash, "hash");
        this.hash = hash;
        this.href = "?list=address&address=" + hash;
    }
}