package App.selectors;
import algorithms.catalog.*;
import algorithms.generation.*;

public class GeneratorSelector extends Selector{
    protected GeneratorCatalog generatorCatalog;

    public GeneratorSelector(GeneratorCatalog generatorCatalog) {
        this.generatorCatalog = generatorCatalog;
    }


    protected void setGenerationOption() {
        String generatorName = terminal.requestGenerationOption(generatorCatalog.showCatalog());
        Generator generator = generatorCatalog.get(generatorName);
        mazeOptions.setGenerator(generator);
    }


    protected void generateMaze() {
        Generator generator = mazeOptions.generator();
        maze = generator.generate(mazeOptions.height(), mazeOptions.width());
    }
}
