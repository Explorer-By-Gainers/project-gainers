package com.explorer.project.handler.connection;

import com.explorer.project.handler.connection.event.Connect;
import com.explorer.project.handler.connection.event.Disconnect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConnectionHandler {

    private final Connect connect;
    private final Disconnect disconnect;

    public Mono<Void> process(Connection connection, JSONObject json) {
        String event = json.getString("event");
        log.info("[process] event : {}", event);
        switch (event) {
            case "connect":
                connect.process(connection, json).subscribe();
                break;
            case "disconnect":
                disconnect.process(json).subscribe();
                break;
        }
        return Mono.empty();
    }

}
