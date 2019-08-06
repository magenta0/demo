package com.example.demo;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Greeting {
    @Nullable
    private String content;

    @Nullable
    public final String getContent() {
        return this.content;
    }

    public final void setContent(@Nullable String var1) {
        this.content = var1;
    }

    public Greeting() {
    }

    public Greeting(@NotNull String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.content = content;
    }
}
