package User;

import climb.*;
import generators.*;
import maze.*;
import solvers.*;

import java.util.List;
import java.util.Scanner;

public class DecisionHandler {
    int minSide, maxSide;
    private Terminal terminal;
    private List<Solver> solvers;
    private List<Generator> generators;


    protected void initializeHandler(int minSide, int maxSide){
        this.minSide = minSide;
        this.maxSide = maxSide;
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


    protected void sayHello(){
        terminal.print("В этой программе возможно сгенерировать случайный лабиринт, выбрать способ поиска решения и увидеть историю его прохождения."
                + "\n Чтобы управлять программой используйте числа."
                + "\n Для генерации лабиринта выберите 4 параметра: "
        );
    }


    protected Maze createMaze() {
        int height = requestUserHeight(), width = requestUserWidth();
        Generator mazeGenerator = requestGenerationOption();
        return mazeGenerator.generate(height, width);
    }

    protected int requestUserHeight(){
        terminal.print("Выберите высоту лабиринта. ");
        int height = terminal.getUserInt(minSide, maxSide);
        return height;
    }

    protected int requestUserWidth(){
        terminal.print("Выберите ширину лабиринта. ");
        int width = terminal.getUserInt(minSide, maxSide);
        return width;
    }

    protected Generator requestGenerationOption(){
        terminal.print("Выберите алгоритм генерации лабиринта. ");
        int algoOfCreation = terminal.getUserInt(1, 2);
        return generators.get(algoOfCreation + 1);
    }

    protected Solver requestSolverOption(){
        // cначала очисти предыдущий поиск

        terminal.print("Выберите алгоритм поиска лабиринта. ");
        int algoOfSearch = terminal.getUserInt(1, 2);
        return solvers.get(algoOfSearch + 1);
    }


    protected boolean isContinue(){
        terminal.print("Вы хотите продолжить генерацию и прохождение лабиринтов ?"
                + "\n 1. Да. "
                + "\n 2. Нет. (программа завершится)"
        );

        int userChoice = terminal.getUserInt();
        return userChoice == 1 ? true : false;
    }

    protected int getUserChoice(){
        terminal.print("Как вы желаете продолжить генерацию и прохождение ?"
                + "\n 1. Начать все заново. "
                + "\n 2. Сгенерировать новый лабиринт. (Алгоритм поиска сохранится)"
                + "\n 3. Использовать другой алгоритм поиска. (Лабиринт сохранится)"
        );

        int userChoice = terminal.getUserInt(1, 3);
        return userChoice;
    }


}
