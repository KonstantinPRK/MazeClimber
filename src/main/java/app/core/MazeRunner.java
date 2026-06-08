package app.core;
import app.selectors.Selector;
import org.springframework.stereotype.Component;

@Component
public class MazeRunner {
    private Selector selector;
    private Climber climber;

    public MazeRunner(Selector selector, Climber climber) {
        this.selector = selector;
        this.climber = climber;
    }

    public void start(){
        mazeClimbing();
    }


    private void mazeClimbing() {
        int numOfLaunches = 0;

        do{
            selection(numOfLaunches);
            climbing();
            numOfLaunches++;
        } while (selector.isContinue());
    }


    private void selection(int numOfLaunches){
        selector.setMaze(numOfLaunches);
        selector.setSolution(numOfLaunches);
        selector.setRenderer(numOfLaunches);
    }


    private void climbing() {
        climber.createNewMaze();
        climber.solveMaze();
        climber.toRenderMazeSolution();
    }
}
