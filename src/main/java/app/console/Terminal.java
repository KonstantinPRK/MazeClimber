package app.console;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.configuration.CurrentSize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Terminal {
    private final Output output;
    private final Input input;
    private final String MEANING_IS_NOT_EXIST = "значение отсутствует";

    public Terminal(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    public void sayHello() {
        output.print("В этой программе возможно сгенерировать случайный лабиринт, выбрать способ поиска решения и увидеть историю его прохождения."
                + "\n Чтобы управлять программой используйте числа."
                + "\n Для генерации лабиринта выберите необходимые параметры: ");
    }

    public void print(String... str) {
        for (String line : str) output.print(line);
    }

    // Выбор условий – перед вопросом одна пустая строка
    public boolean askToNeedNewSize() {
        output.printEmptyLines(1);
        output.print(output.bold("Какой размерности должен быть новый лабиринт ?")
                + "\n 1. Изменить размерность. "
                + "\n 2. Использовать текущую размерность. "
        );
        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewGenerator() {
        output.printEmptyLines(1);
        output.print(output.bold("Каким способом будет сгенерирован новый лабиринт ?")
                + "\n 1. Выбрать новый генератор. "
                + "\n 2. Использовать текущий генератор. "
        );
        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewSolver() {
        output.printEmptyLines(1);
        output.print(output.bold("Каким способом вы желаете найти выход из лабиринта?")
                + "\n 1. Новый способ прохождения лабиринта. "
                + "\n 2. Использовать ранее выбранный алгоритм. "
        );
        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }

    public boolean askToNeedNewRenderer() {
        output.printEmptyLines(1);
        output.print(output.bold("Как вы желаете продолжить отображение лабиринта?")
                + "\n 1. Новый способ зарисовки лабиринта. "
                + "\n 2. Использовать ранее выбранный алгоритм зарисовки. "
        );
        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }


    public boolean askContinueOption() {
        output.printEmptyLines(3);
        output.print(output.bold("Вы хотите продолжить генерацию и прохождение лабиринтов ?")
                + "\n 1. Да. "
                + "\n 2. Нет. (программа завершится)"
        );
        int userChoice = input.getUserInt(1, 2);
        return userChoice == 1;
    }


    public void applyCurrentOptions(CurrentSize size) {
        String currentHeight = (size == null) ? MEANING_IS_NOT_EXIST : String.valueOf(size.height());
        String currentWidth = (size == null) ? MEANING_IS_NOT_EXIST : String.valueOf(size.width());
        output.print(output.bold("Текущие параметры (размер лабиринта): ")
                + "\n Высота: " + currentHeight
                + "\n Ширина: " + currentWidth
        );
    }

    public void applyCurrentOptions(Generator generator) {
        String currentGenerator = (generator == null) ? MEANING_IS_NOT_EXIST : generator.getName();
        output.print(output.bold("Текущие параметры (алгоритм генерации): ") + currentGenerator);
    }

    public void applyCurrentOptions(Solver solver) {
        String currentSolver = (solver == null) ? MEANING_IS_NOT_EXIST : solver.getName();
        output.print(output.bold("Текущие параметры (алгоритм поиска решения): ") + currentSolver);
    }

    public void applyCurrentOptions(Renderer renderer) {
        String currentRenderer = (renderer == null) ? MEANING_IS_NOT_EXIST : renderer.getName();
        output.print(output.bold("Текущие параметры (алгоритм отображения лабиринта): ") + currentRenderer);
    }

    // Запрос информации – одна пустая строка перед вопросом
    public CurrentSize requestSize(int minSide, int maxSide) {
        output.printEmptyLines(1);
        output.print(output.bold("Выберите высоту лабиринта. "));
        int height = input.getUserInt(minSide, maxSide);

        output.print(output.bold("Выберите ширину лабиринта. "));
        int width = input.getUserInt(minSide, maxSide);

        return new CurrentSize(height, width);
    }

    public String requestGenerationOption(List<String> catalog) {
        output.printEmptyLines(1);
        output.print(output.bold("Выберите алгоритм генерации лабиринта. "));
        output.printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }

    public String requestSolverOption(List<String> catalog) {
        output.printEmptyLines(1);
        output.print(output.bold("Выберите алгоритм поиска пути. "));
        output.printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }

    public String requestRendererOption(List<String> catalog) {
        output.printEmptyLines(1);
        output.print(output.bold("Выберите алгоритм отрисовки лабиринта. "));
        output.printCatalog(catalog);
        int numberOfPosition = input.getUserInt(1, catalog.size());
        return catalog.get(numberOfPosition - 1);
    }
}