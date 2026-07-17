package app.core;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private final Map<String, MazeSession> sessions;
    private int counter = 1;


    public SessionManager(){
        this.sessions = new ConcurrentHashMap<>();
    }


    public synchronized MazeSession createSession() {
        MazeSession session = new MazeSession();
        String stringId = String.valueOf(nextId());
        sessions.put(stringId, session);
        return session;
    }


    private int nextId() {
        return counter++;
    }


    //неиспользуемый
    public MazeSession getSession(Integer intSessionID){
        String id = String.valueOf(intSessionID);
        return sessions.get(id);
    }
}
