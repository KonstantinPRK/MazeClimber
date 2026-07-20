package app.core;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.console.Messenger;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Компонент, выполняющий основные действия с лабиринтом: генерацию,
 * поиск пути, отрисовку и управление циклом работы программы.
 * Взаимодействует с пользователем через {@link Messenger}.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class Climber {
    private final Messenger msg;
    private List<Coordinate> path;
    private Maze maze;
    private String CONTINUE = "Да",
            STOP = "Нет. (программа завершится)";


    /**
     * Конструктор, инициализирующий мессенджер для общения с пользователем.
     *
     * @param messenger компонент для вывода сообщений и получения ввода
     */
    public Climber(Messenger messenger) {
        this.msg = messenger;
    }


    /**
     * Выводит приветственное сообщение и краткую инструкцию по использованию программы.
     */
    protected void sayHello() {
        msg.showInformation("В этой программе возможно сгенерировать случайный лабиринт, выбрать способ поиска решения и увидеть историю его прохождения.",
                "Чтобы управлять программой используйте числа.",
                "Для генерации лабиринта выберите необходимые параметры: ");
    }


    /**
     * Генерирует новый лабиринт на основе текущих параметров сессии
     * и сохраняет его в поле {@link #maze}.
     *
     * @param session текущая сессия, содержащая выбранный генератор и размер
     */
    protected void createNewMaze(MazeSession session) {
        Generator generator = session.generator();
        maze = generator.generate(session.size());
    }


    /**
     * Решает текущий лабиринт с помощью алгоритма из сессии
     * и сохраняет найденный путь в поле {@link #path}.
     *
     * @param session текущая сессия, содержащая выбранный солвер
     */
    protected void solveMaze(MazeSession session) {
        Solver solver = session.solver();
        path = solver.solve(maze);
    }


    /**
     * Отрисовывает лабиринт и его решение с помощью рендерера из сессии.
     * Выводит на экран как пустой лабиринт, так и лабиринт с путём.
     *
     * @param session текущая сессия, содержащая выбранный рендерер
     */
    protected void toRenderMazeSolution(MazeSession session) {
        Renderer renderer = session.renderer();
        String mazeDraw = renderer.render(maze),
                solvedMazeDraw = renderer.render(maze, path);

        msg.unformattedPrint(mazeDraw, solvedMazeDraw);
    }


    /**
     * Запрашивает у пользователя желание продолжить работу.
     * В случае положительного ответа увеличивает счётчик запусков сессии.
     *
     * @param session текущая сессия, у которой вызывается {@link MazeSession#newLaunch()}
     * @return true, если пользователь хочет продолжить, иначе false
     */
    public boolean isContinue(MazeSession session) {
        msg.showInformation("Вы хотите продолжить генерацию и прохождение лабиринтов ?");
        boolean userRespond = msg.requestRespond(CONTINUE, STOP).equals(CONTINUE);
        if (userRespond) session.newLaunch();
        return userRespond;
    }
}