package app.selectors;

import org.springframework.stereotype.Component;

@Component
public interface Selector {
    public void setOption(int numOfLaunches);
}