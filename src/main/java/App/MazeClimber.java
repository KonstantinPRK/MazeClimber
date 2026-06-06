package App;
import App.selectors.Selector;

public class MazeClimber {
    private Selector selector;

    public void start(){
        selector = new Selector();
        selector.initializeSelector();
        climbing();
    }

    private void climbing(){
        int numOfLaunches = 0;

        do{
            selector.setMaze(numOfLaunches);
            selector.setSolution(numOfLaunches);
            selector.setRenderer(numOfLaunches);

            numOfLaunches++;
        } while (selector.isContinue());
    }
}
