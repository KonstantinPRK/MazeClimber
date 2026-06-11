package app.core;
import app.selectors.Selector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MazeRunner {
    private final Selector sizeSelector, generatorSelector, solverSelector, rendererSelector;
    private final Climber climber;

    public MazeRunner(@Qualifier("sizeSelector") Selector sizeSelector,
                      @Qualifier("generatorSelector") Selector generatorSelector,
                      @Qualifier("solverSelector") Selector solverSelector,
                      @Qualifier("rendererSelector") Selector rendererSelector,
                      Climber climber) {
        this.sizeSelector = sizeSelector;
        this.generatorSelector = generatorSelector;
        this.solverSelector = solverSelector;
        this.rendererSelector = rendererSelector;
        this.climber = climber;
    }

    public void start(){
        climber.sayHello();
        mazeClimbing();
    }


    private void mazeClimbing() {
        int numOfLaunches = 0;

        do{
            selection(numOfLaunches);
            climbing();
            numOfLaunches++;
        } while (climber.isContinue());
    }


    private void selection(int numOfLaunches){
        sizeSelector.setOption(numOfLaunches);
        generatorSelector.setOption(numOfLaunches);
        solverSelector.setOption(numOfLaunches);
        rendererSelector.setOption(numOfLaunches);
    }


    private void climbing() {
        climber.createNewMaze();
        climber.solveMaze();
        climber.toRenderMazeSolution();
    }
}
