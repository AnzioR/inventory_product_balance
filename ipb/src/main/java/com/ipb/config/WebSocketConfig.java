//package com.ipb.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//  @Override
//  public void configureMessageBroker(MessageBrokerRegistry registry) {
//    registry.enableSimpleBroker("/topic"); // 메시지 브로커를 "/topic"으로 구성합니다.
//    registry.setApplicationDestinationPrefixes("/app");
//  }
//
//  @Override
//  public void registerStompEndpoints(StompEndpointRegistry registry) {
//    registry.addEndpoint("/ws").withSockJS(); // WebSocket 엔드포인트를 "/ws"로 등록합니다.
//  }
//}