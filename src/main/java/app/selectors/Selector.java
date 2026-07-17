package app.selectors;

import app.core.MazeSession;
import org.springframework.stereotype.Component;

@Component
public interface Selector {
    void setOption(MazeSession session);
}