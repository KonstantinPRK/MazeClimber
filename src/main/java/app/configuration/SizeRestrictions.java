package app.configuration;

import org.springframework.stereotype.Component;

@Component
public class SizeRestrictions {
    private final int minSize;
    private final int maxSize;

    // Значения по умолчанию
    public SizeRestrictions() {
        this.minSize = 1;
        this.maxSize = 100;
    }

    public int minSize() { return minSize; }
    public int maxSize() { return maxSize; }
}