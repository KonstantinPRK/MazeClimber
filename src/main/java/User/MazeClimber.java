package User;

import maze.*;
import java.util.List;

public class MazeClimber {
    boolean mazeNotExist = true, hasNoSolution = true;
    private DecisionHandler handler;
    private List<Object> mazeStates; // возможно тут будет храниться путь прохождения, а возможно и нет
    private Maze maze;


    public MazeClimber(DecisionHandler handler){
        int minSide = 1, maxSide = 50;
        this.handler = handler;
        handler.initializeHandler(minSide, maxSide);
    }


    public void start(){
        handler.sayHello();
        climbing();
    }


    private void climbing(){
        do{
            if(mazeNotExist) createNewMaze();
            if(hasNoSolution) solveMaze();
            showCurrentMaze();
            applySolveReport();
        } while (isContinue());
    }


    private void createNewMaze(){
        maze = handler.createMaze();
    }

    //пока Object так как не уверен что он вообще что-то будет возвращать
    private Object solveMaze(){
        //запуск счетчика времени
        List path;
        handler.requestSolverOption().solve(null, null, null);
        //конец счетчика времени
        return null;
    }

    private void showCurrentMaze(){
        //циклом печать

    }

    private void applySolveReport(){

    }


    private boolean isContinue(){
        if(handler.isContinue()) return false;
        setNewUserChoice();
        return true;
    }


    private void setNewUserChoice(){
        final int RESTART_ALL = 1, NEW_MAZE = 2, NEW_SOLVER = 3;

        switch (handler.getUserChoice()){
            case RESTART_ALL -> {
                mazeNotExist = true;
                hasNoSolution = true;
            }
            case NEW_MAZE -> { 
                mazeNotExist = true;
                hasNoSolution = false;
            }
            case NEW_SOLVER -> {
                mazeNotExist = false;
                hasNoSolution = true;
            }
            default -> throw new RuntimeException();
        }
    }


}
