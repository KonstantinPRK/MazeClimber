package app.algorithms.solution.solvers;

import app.algorithms.solution.Solver;
import app.maze.Cell;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.*;

import static app.maze.Cell.Type.PASSAGE;

@Component
public class BfsSolver implements Solver {

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - веерный поиск, перебор множества вариантов.";
    }


    @Override
    public List<Coordinate> solve(Maze maze) {
        return solve(maze, maze.getEntrance(), maze.getExit());
    }

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