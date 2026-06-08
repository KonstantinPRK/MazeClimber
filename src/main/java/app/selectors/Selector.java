package app.selectors;

import app.console.Terminal;
import app.core.MazeOptions;
import org.springframework.stereotype.Component;


@Component
public class Selector {
    private final Terminal terminal;
    private final MazeOptions options;

    private final MazeSelector mazeSelector;
    private final GeneratorSelector generatorSelector;
    private final SolverSelector solverSelector;
    private final RendererSelector rendererSelector;


    public Selector(Terminal terminal, MazeOptions options,
                    MazeSelector mazeSelector,
                    GeneratorSelector generatorSelector,
                    SolverSelector solverSelector,
                    RendererSelector rendererSelector) {
        this.terminal = terminal;
        this.options = options;
        this.mazeSelector = mazeSelector;
        this.generatorSelector = generatorSelector;
        this.solverSelector = solverSelector;
        this.rendererSelector = rendererSelector;
    }


    public void setMaze(int numOfLaunches) {
        if (numOfLaunches > 0) mazeSelector.isNewOrOldMaze();

        if (options.isNeedNewMaze()) {
            mazeSelector.setSize();
            generatorSelector.setGenerationOption();
        }
    }


    public void setSolution(int numOfLaunches) {
        if (numOfLaunches > 0) solverSelector.isNewOrOldSolver();
        if (options.isNeedNewSolver()) solverSelector.changeSolver();
    }


    public void setRenderer(int numOfLaunches) {
        if (numOfLaunches > 0) rendererSelector.isNewOrOldRenderer();
        if (options.isNeedNewRenderer()) rendererSelector.changeRenderer();
    }


    public boolean isContinue() {
        return terminal.toAskContinueOption();
    }
}