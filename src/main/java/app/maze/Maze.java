package app.maze;

import app.configuration.CurrentSize;
import java.util.Arrays;

/**
 * Представляет лабиринт как двумерную сетку ячеек с определёнными
 * координатами входа и выхода. Обеспечивает доступ к ячейкам и
 * предоставляет копию внутреннего состояния для безопасного использования.
 *
 * @author unknown
 * @version 1.0
 */
public final class Maze {
    private final Cell[][] grid;
    private final Coordinate entrance, exit;


    /**
     * Создаёт лабиринт с заданной сеткой ячеек, точкой входа и выхода.
     *
     * @param grid     двумерный массив ячеек, представляющих лабиринт
     * @param entrance координата входа в лабиринт
     * @param exit     координата выхода из лабиринта
     */
    public Maze(Cell[][] grid, Coordinate entrance, Coordinate exit) {
        this.grid = grid;
        this.entrance = entrance;
        this.exit = exit;
    }


    /**
     * Возвращает ячейку по указанным строке и столбцу.
     *
     * @param row номер строки (индекс)
     * @param col номер столбца (индекс)
     * @return ячейка по заданным координатам
     */
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }


    /**
     * Возвращает размер лабиринта (высоту и ширину) в виде объекта {@link CurrentSize}.
     *
     * @return объект с высотой (количество строк) и шириной (количество столбцов)
     */
    public CurrentSize size() {
        return new CurrentSize(grid.length, grid[0].length);
    }


    /**
     * Возвращает глубокую копию сетки лабиринта.
     * Каждая строка клонируется, чтобы предотвратить внешние модификации.
     *
     * @return копия двумерного массива ячеек
     */
    public Cell[][] getGrid() {
        return Arrays.stream(this.grid)
                .map(Cell[]::clone)
                .toArray(Cell[][]::new);
    }


    /**
     * Возвращает координату входа в лабиринт.
     *
     * @return координата входа
     */
    public Coordinate getEntrance() {
        return entrance;
    }


    /**
     * Возвращает координату выхода из лабиринта.
     *
     * @return координата выхода
     */
    public Coordinate getExit() {
        return exit;
    }
}