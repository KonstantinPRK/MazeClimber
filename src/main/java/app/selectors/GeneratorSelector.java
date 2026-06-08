package app.selectors;

import app.core.MazeOptions;
import app.algorithms.catalog.GeneratorCatalog;
import app.algorithms.generation.Generator;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class GeneratorSelector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final GeneratorCatalog generatorCatalog;

    public GeneratorSelector(Terminal terminal, MazeOptions options, GeneratorCatalog generatorCatalog) {
        this.terminal = terminal;
        this.options = options;
        this.generatorCatalog = generatorCatalog;
    }


    public void setGenerationOption() {
        String generatorName = terminal.requestGenerationOption(generatorCatalog.showCatalog());
        Generator generator = generatorCatalog.get(generatorName);
        options.setGenerator(generator);
    }
}