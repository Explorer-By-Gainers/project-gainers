package com.explorer.project.global.component.session;

import org.springframework.stereotype.Component;
import reactor.netty.Connection;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final ConcurrentHashMap<Long, Connection> idToConnectionMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Connection, Long> connectionToIdMap = new ConcurrentHashMap<>();

    public void add(Long id, Connection connection) {
        idToConnectionMap.put(id, connection);
        connectionToIdMap.put(connection, id);
    }

    public Connection getConnectionById(Long id) {
        return idToConnectionMap.get(id);
    }

    public Long getIdByConnection(Connection connection) {
        return connectionToIdMap.get(connection);
    }

    public void deleteById(Long id) {
        Connection connection = getConnectionById(id);
        if (connection != null) {
            idToConnectionMap.remove(id);
            connectionToIdMap.remove(connection);
        }
    }

    public void deleteByConnection(Connection connection) {
        Long id = getIdByConnection(connection);
        if (id != null) {
            idToConnectionMap.remove(id);
            connectionToIdMap.remove(connection);
        }
    }

    public List<Long> findAllIds() {
        return idToConnectionMap.keySet().stream().toList();
    }

    public List<Connection> findAllConnections() {
        return connectionToIdMap.keySet().stream().toList();
    }

}

