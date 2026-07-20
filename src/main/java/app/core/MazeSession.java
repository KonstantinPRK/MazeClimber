package app.core;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.configuration.CurrentSize;

/**
 * Хранит состояние текущей сессии работы с лабиринтом.
 * Содержит параметры генерации, поиска пути и отрисовки,
 * а также размер лабиринта и количество выполненных запусков.
 */
public class MazeSession {
    private int numOfLaunches = 0;
    private CurrentSize size;
    private Generator generator;
    private Solver solver;
    private Renderer renderer;


    /**
     * Возвращает количество запусков (циклов генерации/решения) в рамках сессии.
     *
     * @return число запусков
     */
    public int numOfLaunches() {
        return numOfLaunches;
    }


    /**
     * Увеличивает счётчик запусков на единицу.
     * Вызывается при начале нового цикла работы с лабиринтом.
     */
    public void newLaunch() {
        numOfLaunches++;
    }


    /**
     * Возвращает текущий размер лабиринта.
     *
     * @return объект {@link CurrentSize} с высотой и шириной
     */
    public CurrentSize size() {
        return size;
    }


    /**
     * Устанавливает размер лабиринта.
     *
     * @param size новый размер лабиринта
     */
    public void setSize(CurrentSize size) {
        this.size = size;
    }


    /**
     * Возвращает текущий алгоритм генерации лабиринта.
     *
     * @return используемый генератор
     */
    public Generator generator() {
        return generator;
    }


    /**
     * Устанавливает алгоритм генерации лабиринта.
     *
     * @param generator новый генератор
     */
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }


    /**
     * Возвращает текущий алгоритм поиска пути (солвер).
     *
     * @return используемый солвер
     */
    public Solver solver() {
        return solver;
    }


    /**
     * Устанавливает алгоритм поиска пути.
     *
     * @param solver новый солвер
     */
    public void setSolver(Solver solver) {
        this.solver = solver;
    }


    /**
     * Возвращает текущий алгоритм отрисовки лабиринта.
     *
     * @return используемый рендерер
     */
    public Renderer renderer() {
        return renderer;
    }


    /**
     * Устанавливает алгоритм отрисовки лабиринта.
     *
     * @param renderer новый рендерер
     */
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}