package com.teamproject.devTalks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.teamproject.devTalks.provider.WebSocketProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketProvider webSocketProvider;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        
        registry
            .addHandler(webSocketProvider, "/web-socket")
            .setAllowedOrigins("*");

    }


}