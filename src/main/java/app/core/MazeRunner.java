package app.core;
import app.selectors.Selector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MazeRunner {
    private final SessionManager sessionManager;
    private final Selector sizeSelector, generatorSelector, solverSelector, rendererSelector;
    private final Climber climber;


    public MazeRunner(@Qualifier("sizeSelector") Selector sizeSelector,
                      @Qualifier("generatorSelector") Selector generatorSelector,
                      @Qualifier("solverSelector") Selector solverSelector,
                      @Qualifier("rendererSelector") Selector rendererSelector,
                      Climber climber,
                      SessionManager sessionManager) {
        this.sizeSelector = sizeSelector;
        this.generatorSelector = generatorSelector;
        this.solverSelector = solverSelector;
        this.rendererSelector = rendererSelector;
        this.climber = climber;
        this.sessionManager = sessionManager;
    }

    public void start(){
        climber.sayHello();
        MazeSession session = sessionManager.createSession();
        mazeClimbing(session);
    }


    private void mazeClimbing(MazeSession session) {
        do{
            selection(session);
            climbing(session);
        } while (climber.isContinue(session));
    }


    private void selection(MazeSession session){
        sizeSelector.setOption(session);
        generatorSelector.setOption(session);
        solverSelector.setOption(session);
        rendererSelector.setOption(session);
    }


    private void climbing(MazeSession session) {
        climber.createNewMaze(session);
        climber.solveMaze(session);
        climber.toRenderMazeSolution(session);
    }
}
