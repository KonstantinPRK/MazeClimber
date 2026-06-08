package app.console;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal {
    private Output output;
    private Input input;

    private final String MEANING_IS_NOT_EXIST = "значение отсутствует";

    public Terminal(Output output, Input input){
        this.output = output;
        this.input = input;
    }

    public void sayHello() {
        output.print("В этой программе возможно сгенерировать случайный лабиринт, выбрать способ поиска решения и увидеть историю его прохождения."
                + "\n Чтобы управлять программой используйте числа."
                + "\n Для генерации лабиринта выберите необходимые параметры: "
        );
    }


    public void applyCurrentOptions(Integer height, Integer width) {
        String currentHeight = (height == null) ? MEANING_IS_NOT_EXIST : String.valueOf(height);
        String currentWidth = (width == null) ? MEANING_IS_NOT_EXIST : String.valueOf(width);
        output.print("Текущие параметры (размер лабиринта): "
                    + "\n Высота: " + currentHeight
                    + "\n Ширина: " + currentWidth
        );
    }

    public void applyCurrentOptions(Generator generator) {
        String currentGenerator = (generator == null) ? MEANING_IS_NOT_EXIST : generator.getName();
        output.print("Текущие параметры (алгоритм генерации): " + currentGenerator);
    }

    public void applyCurrentOptions(Solver solver) {
        String currentSolver = (solver == null) ? MEANING_IS_NOT_EXIST : solver.getName();
        output.print("Текущие параметры (алгоритм поиска решения): " + currentSolver);
    }

    public void applyCurrentOptions(Renderer renderer) {
        String currentRenderer = (renderer == null) ? MEANING_IS_NOT_EXIST : renderer.getName();
        output.print("Текущие параметры (алгоритм отображения лабиринта): " + currentRenderer);
    }




    public boolean askToNeedNewMaze() {
        output.print("Как вы желаете продолжить генерацию ?"
                + "\n 1. Генерация нового лабиринта. "
                + "\n 2. Использовать ранее созданный лабиринт. "
        );

        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewSolver(){
        output.print("Как вы желаете продолжить генерацию ?"
                + "\n 1. Новый способ прохождения лабиринта. "
                + "\n 2. Использовать ранее выбранный алгоритм. "
        );

        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewRenderer(){
        output.print("Как вы желаете продолжить отображение лабиринта ?"
                + "\n 1. Новый способ зарисовки лабиринта. "
                + "\n 2. Использовать ранее выбранный алгоритм зарисовки. "
        );

        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }




    public int requestHeight(int minSide, int maxSide){
        String UserLimitsText = "\n введите число от " + minSide + " до " + maxSide + " включительно: ";
        output.print("Выберите высоту лабиринта. " + UserLimitsText);
        return input.getUserInt(minSide, maxSide);
    }

    public int requestWidth(int minSide, int maxSide){
        String UserLimitsText = "\n введите число от " + minSide + " до " + maxSide + " включительно: ";
        output.print("Выберите ширину лабиринта. " + UserLimitsText);
        return input.getUserInt(minSide, maxSide);
    }

    public String requestGenerationOption(List<String> catalog){
        output.print("Выберите алгоритм генерации лабиринта. ");
        printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }

    public String requestSolverOption(List<String> catalog) {
        output.print("Выберите алгоритм поиска пути. ");
        printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }

    public String requestRendererOption(List<String> catalog) {
        output.print("Выберите алгоритм отрисовки лабиринта. ");
        printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }

    private void printCatalog(List<String> catalog){
        for(int count = 1; count <= catalog.size(); count++){
            String numberOfPosition = count + ".";
            String variantName = " " + catalog.get(count - 1);
            output.print(numberOfPosition + variantName);
        }
    }


    public boolean toAskContinueOption() {
        output.print("Вы хотите продолжить генерацию и прохождение лабиринтов ?"
                + "\n 1. Да. "
                + "\n 2. Нет. (программа завершится)"
        );

        int userChoice = input.getUserInt();
        return userChoice == 1;
    }



}
