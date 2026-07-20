package app.algorithms.solution.solvers;

import app.algorithms.solution.Solver;
import app.maze.Cell;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.*;

import static app.maze.Cell.Type.PASSAGE;

/**
 * Реализация алгоритма поиска пути с использованием поиска в ширину (BFS).
 * Обеспечивает нахождение кратчайшего пути в лабиринте путём обхода всех
 * достижимых ячеек в порядке их удаления от стартовой точки.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class BfsSolver implements Solver {

    /**
     * Возвращает имя алгоритма с кратким описанием.
     *
     * @return строковое название алгоритма
     */
    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - веерный поиск, перебор множества вариантов.";
    }


    /**
     * Находит путь от входа к выходу лабиринта, используя встроенные координаты
     * входа и выхода из объекта {@link Maze}.
     *
     * @param maze лабиринт, в котором ищется путь
     * @return список координат от входа до выхода (включая обе конечные точки),
     *         или пустой список, если путь не найден
     */
    @Override
    public List<Coordinate> solve(Maze maze) {
        return solve(maze, maze.getEntrance(), maze.getExit());
    }


    /**
     * Находит путь между заданными начальной и конечной координатами в лабиринте
     * с использованием алгоритма BFS.
     *
     * @param maze  лабиринт, в котором ищется путь
     * @param start начальная координата
     * @param end   конечная координата
     * @return список координат от start до end (включая обе конечные точки),
     *         или пустой список, если путь не найден или начальная/конечная ячейки
     *         не являются проходами
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Cell[][] grid = maze.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[start.row()][start.col()].type() != PASSAGE ||
                grid[end.row()][end.col()].type() != PASSAGE) {
            return Collections.emptyList();
        }

        Coordinate[][] previousCell = new Coordinate[rows][cols];
        Deque<Coordinate> queue = new ArrayDeque<>();
        queue.add(start);
        previousCell[start.row()][start.col()] = start;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(previousCell, end);
            }

            for (Coordinate neighbor : getNeighbors(current, grid)) {
                if (previousCell[neighbor.row()][neighbor.col()] == null) {
                    previousCell[neighbor.row()][neighbor.col()] = current;
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }


    /**
     * Возвращает список соседних ячеек (проходов) для заданной позиции.
     * Соседями считаются ячейки сверху, снизу, слева и справа, если они являются проходами.
     *
     * @param position текущая координата
     * @param grid     сетка лабиринта
     * @return список координат соседних проходов (не более 4)
     */
    private List<Coordinate> getNeighbors(Coordinate position, Cell[][] grid) {
        List<Coordinate> neighbors = new ArrayList<>(4);
        int row = position.row();
        int col = position.col();

        if (row > 0 && grid[row - 1][col].type() == PASSAGE)
            neighbors.add(new Coordinate(row - 1, col));
        if (row < grid.length - 1 && grid[row + 1][col].type() == PASSAGE)
            neighbors.add(new Coordinate(row + 1, col));
        if (col > 0 && grid[row][col - 1].type() == PASSAGE)
            neighbors.add(new Coordinate(row, col - 1));
        if (col < grid[0].length - 1 && grid[row][col + 1].type() == PASSAGE)
            neighbors.add(new Coordinate(row, col + 1));

        return neighbors;
    }


    /**
     * Восстанавливает путь от конечной точки к начальной, используя массив
     * предыдущих ячеек, и возвращает его в прямом порядке.
     *
     * @param previousCell массив, хранящий для каждой ячейки предыдущую на пути
     * @param end          конечная координата
     * @return список координат от начальной до конечной (включая обе)
     */
    private List<Coordinate> reconstructPath(Coordinate[][] previousCell, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;

        while (current != null && !current.equals(previousCell[current.row()][current.col()])) {
            path.add(current);
            current = previousCell[current.row()][current.col()];
        }

        if (current != null) {
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }
}