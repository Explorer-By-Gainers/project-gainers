package com.explorer.project.handler.connection.event;

import com.explorer.project.global.common.repository.ChannelRepository;
import com.explorer.project.global.component.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class Disconnect {

    private final SessionManager sessionManager;
    private final ChannelRepository channelRepository;

    public Mono<Void> process(JSONObject json) {
        boolean isGroup = json.getBoolean("isGroup");
        log.info("[process] isGroup : {}", isGroup);

        Long id = json.getLong("id");
        sessionManager.deleteById(id);

        if (!isGroup) {
            String channelId = json.getString("channelId");
            log.info("[process] id : {}, channelId : {}", id, channelId);
            return channelRepository.remove(channelId, id).then();
        }
        log.info("[process] id : {}", id);
        return Mono.empty();
    }

}
