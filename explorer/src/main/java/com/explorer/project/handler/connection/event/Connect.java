package com.explorer.project.handler.connection.event;

import com.explorer.project.global.common.repository.ChannelRepository;
import com.explorer.project.global.component.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;

@Slf4j
@Service
@RequiredArgsConstructor
public class Connect {

    private final SessionManager sessionManager;
    private final ChannelRepository channelRepository;

    public Mono<Void> process(Connection connection, JSONObject json) {
        Long id = json.getLong("id");
        boolean isGroup = json.getBoolean("isGroup");
        log.info("[process] id : {}, isGroup : {}", id, isGroup);

        sessionManager.add(id, connection);

        if (isGroup) {
            String channelId = json.getString("channelId");
            log.info("[process] channelId : {}", channelId);
            return channelRepository.add(channelId, id).then();
        }
        return Mono.empty();
    }
}
