package com.explorer.project.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServerImpl implements CommandLineRunner {

    private final ServerInitializer serverInitializer;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Server...");
        serverInitializer.initializeServer().subscribe(
                disposableServer -> {
                    log.info("Server started on port: {}", disposableServer.port());
                },
                error -> {
                    log.error("Failed to start Server: {}", error.getMessage());
                }
        );

    }
}
