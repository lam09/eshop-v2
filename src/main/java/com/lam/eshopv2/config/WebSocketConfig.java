package com.lam.eshopv2.config;

import com.lam.eshopv2.interceptor.HttpHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private HttpHandshakeInterceptor httpHandshakeInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

    }
}
