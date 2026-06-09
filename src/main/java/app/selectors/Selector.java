package app.selectors;

import app.console.Terminal;
import app.core.MazeOptions;
import org.springframework.stereotype.Component;


@Component
public interface Selector {
    public void setOption(int numOfLaunches);
}