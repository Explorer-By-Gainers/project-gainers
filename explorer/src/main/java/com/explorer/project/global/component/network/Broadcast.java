package com.explorer.project.global.component.network;

import com.explorer.project.global.component.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class Broadcast {

    private final SessionManager sessionManager;

    public Mono<Void> process(JSONObject msg) {
        List<Mono<Void>> monoList = sessionManager.findAllConnections().stream()
                .map(connection ->
                        connection.outbound()
                                .sendString(Mono.just(msg.toString() + '\n'))
                                .then()
                )
                .collect(Collectors.toList());
        return Mono.when(monoList).then();
    }

}