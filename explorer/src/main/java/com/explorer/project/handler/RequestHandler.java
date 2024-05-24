package com.explorer.project.handler;

import com.explorer.project.handler.connection.ConnectionHandler;
import com.explorer.project.handler.network.NetworkHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final ConnectionHandler connectionHandler;
    private final NetworkHandler networkHandler;

    public Mono<Void> handleRequest(NettyInbound inbound, NettyOutbound outbound) {
        return inbound
                .receive()
                .asString()
                .flatMap(msg -> {
                    try{
                        JSONObject json = new JSONObject(msg);
                        log.info("Received Json Data: {}", json);
                        inbound.withConnection(connection -> {
                                String type = json.getString("type");
                                log.info("[handleRequest] type : {}", type);
                                switch (type) {
                                    case "connection":
                                        connectionHandler.process(connection, json).subscribe();
                                        break;
                                    case "network":
                                        networkHandler.process(json).subscribe();
                                        break;
                                }
                        });
                        return Mono.empty();
                    } catch (JSONException e) {
                        log.error("{}", e.getMessage());
                        return Mono.empty();
                    }
                })
                .then();
    }

}
