package app.selectors;

import app.core.MazeOptions;
import app.algorithms.catalog.GeneratorCatalog;
import app.algorithms.generation.Generator;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class GeneratorSelector implements Selector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final GeneratorCatalog generatorCatalog;

    public GeneratorSelector(Terminal terminal, MazeOptions options, GeneratorCatalog generatorCatalog) {
        this.terminal = terminal;
        this.options = options;
        this.generatorCatalog = generatorCatalog;
    }

    @Override
    public void setOption(int numOfLaunches){
        if (numOfLaunches > 0) isNewOrOldOption();
        if (options.isNeedNewGenerator()) setGenerationOption();
    }


    public void isNewOrOldOption(){
        terminal.applyCurrentOptions(options.generator());

        if (terminal.askToNeedNewGenerator()) {
            options.needNewGenerator(true);
        } else {
            options.needNewGenerator(false);
        }
    }

    public void setGenerationOption() {
        String generatorName = terminal.requestGenerationOption(generatorCatalog.showCatalog());
        Generator generator = generatorCatalog.getAlgorithm(generatorName);
        options.setGenerator(generator);
    }
}