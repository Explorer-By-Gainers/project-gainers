package com.explorer.project.handler.network.event;

import com.explorer.project.global.component.network.Broadcast;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class Broadcasting {

    private final Broadcast broadcast;

    public Mono<Void> process(JSONObject json) {
        broadcast.process(json).subscribe();
        return Mono.empty();
    }

}
