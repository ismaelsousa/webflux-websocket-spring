package com.websocket.demo.config

import com.websocket.demo.handle.WebSocketHandle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.server.WebSocketService
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy

@Configuration
@EnableWebFlux
class WebConfig:WebFluxConfigurer {
    @Bean
    fun handlerMapping():HandlerMapping{
        val map = mapOf("/path" to WebSocketHandle())
        val order = -1
        return SimpleUrlHandlerMapping(map, order)
    }

    @Override
    fun webSocketService():WebSocketService{
        val strategy = TomcatRequestUpgradeStrategy().apply {
            setMaxSessionIdleTimeout(0)
        }
        return  HandshakeWebSocketService(strategy)
    }
}