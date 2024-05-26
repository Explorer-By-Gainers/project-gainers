package com.explorer.project.server;

import com.explorer.project.handler.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

@Component
@RequiredArgsConstructor
public class ServerInitializer {

    private static final int PORT = 1370;

    private final RequestHandler requestHandler;

    public Mono<? extends DisposableServer> initializeServer() {
        return TcpServer
                .create()
                .port(PORT)
                .handle(requestHandler::handleRequest)
                .bind();
    }
}
