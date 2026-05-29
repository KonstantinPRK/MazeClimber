package User;

public class Main {
    public static void main(String[] args){
    DecisionHandler handler = new DecisionHandler();
    MazeClimber climber = new MazeClimber(handler);
    climber.start();
    }
}
