package app.configuration;

import org.springframework.stereotype.Component;

@Component
public class SizeRestrictions {
        private int minSize = 3;
        private int maxSize = 20;

    public int minSize() { return minSize; }
    public int maxSize() { return maxSize; }
}