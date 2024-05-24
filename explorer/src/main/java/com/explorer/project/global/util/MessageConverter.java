package com.explorer.project.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JavaTimeModule javaTimeModule = new JavaTimeModule();

    public static JSONObject convert(Object o) {
        objectMapper.registerModule(javaTimeModule);
        try {
            String result = objectMapper.writeValueAsString(o);
            return new JSONObject(result);
        } catch (JsonProcessingException ex) {
            log.error("{}", ex.getMessage());
        }
        return null;
    }

}