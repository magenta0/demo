package com.example.demo;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    public void configureMessageBroker(@NotNull MessageBrokerRegistry config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        config.enableSimpleBroker(new String[]{"/topic"});
        config.setApplicationDestinationPrefixes(new String[]{"/app"});
    }

    public void registerStompEndpoints(@NotNull StompEndpointRegistry registry) {
        Intrinsics.checkParameterIsNotNull(registry, "registry");
        registry.addEndpoint(new String[]{"/gs-guide-websocket"}).withSockJS();
    }
}
