package com.websocket.demo.handle

import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

class WebSocketHandle: WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        val out = session.receive().map {
            session.textMessage("Eco: ${it.payloadAsText}")
        }
        return session.send(out)
    }


}