package com.explorer.project.global.component.network;

import com.explorer.project.global.component.session.SessionManager;
import com.explorer.project.global.exception.network.NetworkErrorCode;
import com.explorer.project.global.exception.network.NetworkException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;

@Slf4j
@Component
@RequiredArgsConstructor
public class Unicast {

    private final SessionManager sessionManager;

    public Mono<Void> process(Long id, JSONObject msg) {
        return checkConnection(id)
                .flatMap(connection ->
                        connection.outbound()
                                .sendString(Mono.just(msg.toString() + '\n'))
                                .then()
                );
    }

    private Mono<Connection> checkConnection(Long id) {
        Connection connection = sessionManager.getConnectionById(id);
        if (connection == null) {
            return Mono.error(new NetworkException(NetworkErrorCode.NOT_CONNECTION));
        }
        return Mono.just(connection);
    }

}