package app.core;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Управляет созданием и хранением сессий лабиринта.
 * Каждая сессия получает уникальный числовой идентификатор.
 * Использует потокобезопасное хранилище {@link ConcurrentHashMap}.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class SessionManager {
    private final Map<String, MazeSession> sessions;
    private final AtomicInteger counter = new AtomicInteger(1);


    /**
     * Конструктор, инициализирующий пустое хранилище сессий.
     */
    public SessionManager() {
        this.sessions = new ConcurrentHashMap<>();
    }


    /**
     * Создаёт новую сессию лабиринта, присваивает ей уникальный
     * идентификатор (начиная с 1) и сохраняет в хранилище.
     *
     * @return созданная сессия {@link MazeSession}
     */
    public MazeSession createSession() {
        MazeSession session = new MazeSession();
        String stringId = String.valueOf(counter.getAndIncrement());
        sessions.put(stringId, session);
        return session;
    }


    /**
     * Возвращает сессию по её числовому идентификатору.
     *
     * @param intSessionID числовой идентификатор сессии
     * @return найденная сессия или {@code null}, если сессия с таким ID не существует
     */
    public MazeSession getSession(Integer intSessionID) {
        String id = String.valueOf(intSessionID);
        return sessions.get(id);
    }
}