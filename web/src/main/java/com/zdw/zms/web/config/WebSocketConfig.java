package com.zdw.zms.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //定义一个前缀为/chart的endpoint ，开启socketjs支持
        registry.addEndpoint("/chat").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //开启消息管道，与前端进行通讯，设置消息代理前缀，如果消息的前缀是/topic，那么消息就会转发给消息
        //代理，然后广播给当前连接的客户端，一般配合注解@SendTo使用
        registry.enableSimpleBroker("/topic");
        //设置应用程序的websocket访问前缀，过滤出需要注解方法处理的消息，一般与注解@MessageMapping使用
        //相当与requestMapping注解
        registry.setApplicationDestinationPrefixes("/zms");
    }
}
