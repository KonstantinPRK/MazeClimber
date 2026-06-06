package App.selectors;

import App.configuration.*;
import algorithms.catalog.*;
import console.*;
import maze.*;

import java.util.List;
import java.util.Scanner;

public class Selector {
    private MazeSelector mazeSelector;
    private GeneratorSelector generatorSelector;
    private SolverSelector solverSelector;
    private RendererSelector renderSelector;

    protected Terminal terminal;
    protected MazeOptions mazeOptions;
    protected Maze maze;
    protected List<Coordinate> path;

    public void initializeSelector(){
        mazeSelector = new MazeSelector(new SizeRestrictions(0, 100));
        generatorSelector = new GeneratorSelector(new GeneratorCatalog());
        solverSelector = new SolverSelector(new SolverCatalog());
        renderSelector = new RendererSelector(new RendererCatalog());

        terminal = new Terminal(
                new Output(System.out),
                new Input(new Scanner(System.in))
        );
        terminal.sayHello();
    }



    public void setMaze(int numOfLaunches) {
        if(numOfLaunches > 0) mazeSelector.isNewOrOldMaze();
        if(mazeOptions.isNeedNewMaze()) createNewMaze();
    }
    protected void createNewMaze(){
        mazeSelector.setSize();
        generatorSelector.setGenerationOption();
        generatorSelector.generateMaze();
    }



    public void setSolution(int numOfLaunches){
        if(numOfLaunches > 0) solverSelector.isNewOrOldSolver();
        if(mazeOptions.isNeedNewSolver()) solverSelector.changeSolver();
        solverSelector.solveMaze();
    }



    public void setRenderer(int numOfLaunches) {
        if(numOfLaunches > 0) renderSelector.isNewOrOldRenderer();
        if(mazeOptions.isNeedNewRenderer()) renderSelector.changeRenderer();
        renderSelector.toRenderMazeSolution();
    }



    public boolean isContinue(){
        return terminal.toAskContinueOption();
    }
}
