package app.algorithms.solution.solvers;

import app.algorithms.solution.Solver;
import app.maze.Cell;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.*;

import static app.maze.Cell.Type.PASSAGE;

@Component
public class AStarSolver implements Solver {

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - поиск кратчайшего пути.";
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

        if (grid[start.row()][start.col()].type() != PASSAGE || grid[end.row()][end.col()].type() != PASSAGE) {
            return Collections.emptyList();
        }

        int[][] costFromStart = new int[rows][cols];
        for (int[] row : costFromStart) Arrays.fill(row, Integer.MAX_VALUE);
        costFromStart[start.row()][start.col()] = 0;

        int[][] estimatedTotalCost = new int[rows][cols];
        for (int[] row : estimatedTotalCost) Arrays.fill(row, Integer.MAX_VALUE);
        estimatedTotalCost[start.row()][start.col()] = estimateDistance(start, end);

        Coordinate[][] previousCell = new Coordinate[rows][cols];
        PriorityQueue<Coordinate> openSet = new PriorityQueue<>(Comparator.comparingInt(cell -> estimatedTotalCost[cell.row()][cell.col()]));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Coordinate current = openSet.poll();

            if (estimatedTotalCost[current.row()][current.col()] !=
                    costFromStart[current.row()][current.col()] + estimateDistance(current, end)) {
                continue;
            }

            if (current.equals(end)) {
                return reconstructPath(previousCell, end);
            }

            for (Coordinate neighbor : getNeighbors(current, grid)) {
                int newCostFromStart = costFromStart[current.row()][current.col()] + 1;

                if (newCostFromStart < costFromStart[neighbor.row()][neighbor.col()]) {
                    previousCell[neighbor.row()][neighbor.col()] = current;
                    costFromStart[neighbor.row()][neighbor.col()] = newCostFromStart;

                    estimatedTotalCost[neighbor.row()][neighbor.col()] = newCostFromStart + estimateDistance(neighbor, end);
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    private int estimateDistance(Coordinate first, Coordinate second) {
        return Math.abs(first.row() - second.row()) + Math.abs(first.col() - second.col());
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

        while (current != null) {
            path.add(current);
            current = previousCell[current.row()][current.col()];
        }

        Collections.reverse(path);
        return path;
    }
}