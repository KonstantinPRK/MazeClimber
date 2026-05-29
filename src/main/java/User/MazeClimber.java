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

    //названия вызовов могут быть изменены для улучшения читаемости позднее - установка флагов для следующего круга прохождения
    private void setNewUserChoice(){
        switch (handler.getUserChoice()){
            case 1 -> {
                mazeNotExist = true;
                hasNoSolution = true;
            }
            case 2 -> {
                mazeNotExist = true;
                hasNoSolution = false;
            }
            case 3 -> {
                mazeNotExist = false;
                hasNoSolution = true;
            }
            default -> throw new RuntimeException();
        }
    }


}
