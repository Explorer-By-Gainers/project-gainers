package com.explorer.project.handler.network.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class Unicasting {

    public Mono<Void> process(JSONObject json) {
        return Mono.empty();
    }

}
