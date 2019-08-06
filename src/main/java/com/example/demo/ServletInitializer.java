package com.example.demo;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public final class ServletInitializer extends SpringBootServletInitializer {
	@NotNull
	protected SpringApplicationBuilder configure(@NotNull SpringApplicationBuilder application) {
		Intrinsics.checkParameterIsNotNull(application, "application");
		SpringApplicationBuilder var10000 = application.sources(new Class[]{DemoApplication.class});
		Intrinsics.checkExpressionValueIsNotNull(var10000, "application.sources(DemoApplication::class.java)");
		return var10000;
	}
}
