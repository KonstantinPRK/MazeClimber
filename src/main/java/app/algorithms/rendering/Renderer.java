package app.algorithms.rendering;

import app.maze.Coordinate;
import app.maze.Maze;
import java.util.List;

/**
 * Интерфейс, определяющий алгоритм отрисовки лабиринта.
 * Реализации должны уметь отображать лабиринт как в чистом виде,
 * так и с выделенным путём решения.
 *
 * @author unknown
 * @version 1.0
 */
public interface Renderer {

    /**
     * Отрисовывает лабиринт без отображения пути решения.
     *
     * @param maze лабиринт для отрисовки
     * @return строковое представление лабиринта
     */
    String render(Maze maze);


    /**
     * Отрисовывает лабиринт с отображением пути решения.
     *
     * @param maze лабиринт для отрисовки
     * @param path список координат пути (может быть пустым или {@code null})
     * @return строковое представление лабиринта с выделенным путём
     */
    String render(Maze maze, List<Coordinate> path);


    /**
     * Возвращает имя алгоритма отрисовки для отображения в интерфейсе пользователя.
     *
     * @return строковое название алгоритма
     */
    String getName();
}