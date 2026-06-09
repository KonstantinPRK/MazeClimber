package app.console;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.configuration.CurrentSize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal {
    private Output output;
    private Input input;

    private final String
            MEANING_IS_NOT_EXIST = "значение отсутствует";

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



    //Выбор условий
    public boolean askToNeedNewSize() {
        output.print("Какой размерности должен быть новый лабиринт ?"
                + "\n 1. Изменить размерность. "
                + "\n 2. Использовать текущую размерность. "
        );

        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewGenerator(){
        output.print("Каким способом будет сгенерирован новый лабиринт ?"
                + "\n 1. Выбрать новый генератор. "
                + "\n 2. Использовать текущий генератор. "
        );

        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewSolver(){
        output.print("Каким способом вы желаете найти выход из лабиринта?"
                + "\n 1. Новый способ прохождения лабиринта. "
                + "\n 2. Использовать ранее выбранный алгоритм. "
        );

        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewRenderer(){
        output.print("Как вы желаете продолжить отображение лабиринта?"
                + "\n 1. Новый способ зарисовки лабиринта. "
                + "\n 2. Использовать ранее выбранный алгоритм зарисовки. "
        );

        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askContinueOption() {
        output.print("Вы хотите продолжить генерацию и прохождение лабиринтов ?"
                + "\n 1. Да. "
                + "\n 2. Нет. (программа завершится)"
        );

        int userChoice = input.getUserInt();
        return userChoice == 1;
    }



    //Представление текущих настроек
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



    //Запрос информации у пользователя
    public CurrentSize requestSize(int minSide, int maxSide){
        output.print("Выберите высоту лабиринта. ");
        int height = input.getUserInt(minSide, maxSide);

        output.print("Выберите ширину лабиринта. ");
        int width = input.getUserInt(minSide, maxSide);

        return new CurrentSize(height, width);
    }

    public String requestGenerationOption(List<String> catalog){
        output.print("Выберите алгоритм генерации лабиринта. ");
        output.printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }

    public String requestSolverOption(List<String> catalog) {
        output.print("Выберите алгоритм поиска пути. ");
        output.printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }

    public String requestRendererOption(List<String> catalog) {
        output.print("Выберите алгоритм отрисовки лабиринта. ");
        output.printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }
}
