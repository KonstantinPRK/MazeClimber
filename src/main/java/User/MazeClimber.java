package User;

import climb.*;
import generators.*;
import maze.*;
import solvers.*;

import java.util.List;
import java.util.Scanner;

public class MazeClimber {
    private int minHeight = 1, minWidth = 1, maxWidth = 50, maxHeight = 50;
    boolean mazeNotExist = true, hasNoSolution = true;
    private List<Generator> generators;
    private List<Solver> solvers;
    private Terminal terminal;
    private Maze maze;


    public MazeClimber(){
        terminal = new Terminal(System.out, new Scanner(System.in));

        generators = List.of(
                new FirstGenerator(),
                new SecondGenerator()
        );

        solvers = List.of(
                new FirstSolver(),
                new SecondSolver()
        );
    }


    public void start(){
        hello();
        climbing();
    }


    private void hello(){
        terminal.print("В этой программе возможно сгенерировать случайный лабиринт, выбрать способ поиска решения и увидеть историю его прохождения."
                + "\n Чтобы управлять программой используйте числа."
                + "\n Для генерации лабиринта выберите 4 параметра: "
        );
    }

    private void climbing(){
        if(mazeNotExist) createMaze();
        if(hasNoSolution) solveMaze();
        showCurrentMaze();
        isContinue();
    }


    private void createMaze(){
        //создание лабиринта
        int height = requestUserHeight();
        int width = requestUserWidth();
        maze = requestGenerationOption().generate(height, width);
    }

    private void solveMaze(){
        //запуск счетчика времени
        List path = requestSolverOption().solve(null, null, null);
        //конец счетчика времени

    }

    private void showCurrentMaze(){
        //циклом
    }

    private void isContinue(){
        if(gameIsOFF()) return;
        makeUserChoice();
    }


    private int requestUserHeight(){
        terminal.print("Выберите высоту лабиринта. ");
        int height = terminal.getUserInt(minHeight, maxHeight);
        return height;
    }

    private int requestUserWidth(){
        terminal.print("Выберите ширину лабиринта. ");
        int width = terminal.getUserInt(minWidth, maxWidth);
        return width;
    }

    private Generator requestGenerationOption(){
        terminal.print("Выберите алгоритм генерации лабиринта. ");
        int algoOfCreation = terminal.getUserInt(1, 2);
        return generators.get(algoOfCreation + 1);
    }

    private Solver requestSolverOption(){
        // cначала очисти предыдущий поиск

        terminal.print("Выберите алгоритм поиска лабиринта. ");
        int algoOfSearch = terminal.getUserInt(1, 2);
        return solvers.get(algoOfSearch + 1);
    }


    private boolean gameIsOFF(){
        terminal.print("Вы хотите продолжить генерацию и прохождение лабиринтов ?"
                + "\n 1. Да. "
                + "\n 2. Нет. (программа завершится)"
        );

        int userChoice = terminal.getUserInt();
        return userChoice != 1;
    }

    private void makeUserChoice(){
        terminal.print("Как вы желаете продолжить генерацию и прохождение ?"
                + "\n 1. Начать все заново. "
                + "\n 2. Сгенерировать новый лабиринт. (Алгоритм поиска сохранится)"
                + "\n 3. Использовать другой алгоритм поиска. (Лабиринт сохранится)"
        );

        int userChoice = terminal.getUserInt(1, 3);
        switch (userChoice){
            case 1 -> {
                mazeNotExist = true;
                hasNoSolution = true;
                climbing();
            }
            case 2 -> {
                mazeNotExist = true;
                hasNoSolution = false;
                createMaze();
            }
            case 3 -> {
                mazeNotExist = false;
                hasNoSolution = true;
                requestSolverOption();
            }
            default -> System.out.println("ошибка");
        }
    }


}
