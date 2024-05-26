package com.explorer.project.handler.network;

import com.explorer.project.handler.network.event.Broadcasting;
import com.explorer.project.handler.network.event.Multicasting;
import com.explorer.project.handler.network.event.Unicasting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class NetworkHandler {

    private final Broadcasting broadcasting;
    private final Multicasting multicasting;
    private final Unicasting unicasting;

    public Mono<Void> process(JSONObject json) {
        String event = json.getString("event");
        log.info("[process] event : {}", event);
        switch (event) {
            case "broadcast":
                broadcasting.process(json).subscribe();
                break;
            case "multicast":
                multicasting.process(json).subscribe();
                break;
            case "unicast":
                unicasting.process(json).subscribe();
                break;
        }
        return Mono.empty();
    }

}
