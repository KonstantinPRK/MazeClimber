package app.algorithms.generation.generators;

import app.algorithms.generation.Generator;
import app.configuration.CurrentSize;
import app.maze.Cell;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.*;

import static app.maze.Cell.Type.*;

@Component
public class SidewinderGenerator implements Generator {
    private final Random random;
    private Cell[][] grid;
    private Coordinate entrance, exit;

    public SidewinderGenerator(Random random) {
        this.random = random;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - Построчный лабиринт, горизонтальные проходы.";
    }

    @Override
    public Maze generate(CurrentSize size) {
        initGrid(size);
        createMaze(size);
        finalizeMaze();
        return new Maze(grid, entrance, exit);
    }

    private void initGrid(CurrentSize size) {
        int rows = 2 * size.height() + 1;
        int cols = 2 * size.width() + 1;
        grid = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell(new Coordinate(row, col), WALL);
            }
        }
    }

    private boolean isOuterNonCorner(int row, int col) {
        int maxRow = grid.length - 1;
        int maxCol = grid[0].length - 1;
        boolean onEdge = (row == 0 || row == maxRow || col == 0 || col == maxCol);
        boolean corner = (row == 0 || row == maxRow) && (col == 0 || col == maxCol);
        return onEdge && !corner;
    }

    private void createMaze(CurrentSize size) {
        int height = size.height();
        int width = size.width();
        for (int row = 1; row <= 2 * height - 1; row += 2) {
            int runStart = 1;
            for (int col = 1; col <= 2 * width - 1; col += 2) {
                markAsPassage(row, col);
                boolean closeRun = (col == 2 * width - 1) || (row != 2 * height - 1 && random.nextBoolean());
                if (closeRun) {
                    int chosenCol = random.nextInt((col - runStart) / 2 + 1) * 2 + runStart;
                    markAsPassage(row - 1, chosenCol);
                    runStart = col + 2;
                } else {
                    markAsPassage(row, col + 1);
                }
            }
        }
    }

    private void markAsPassage(int row, int col) {
        grid[row][col] = new Cell(new Coordinate(row, col), PASSAGE);
    }

    private void finalizeMaze() {
        setGates();
    }

    private void setGates() {
        List<Coordinate> north = getBorderCoords(0);
        List<Coordinate> south = getBorderCoords(grid.length - 1);
        if (north.isEmpty() || south.isEmpty()) {
            entrance = exit = new Coordinate(1, 1);
            return;
        }
        entrance = connectToInside(north);
        exit = connectToInside(south);
    }

    private List<Coordinate> getBorderCoords(int row) {
        List<Coordinate> coords = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            if (isOuterNonCorner(row, col)) {
                coords.add(new Coordinate(row, col));
            }
        }
        return coords;
    }

    private Coordinate connectToInside(List<Coordinate> borderCoords) {
        Coordinate chosen = borderCoords.get(random.nextInt(borderCoords.size()));
        int row = chosen.row();
        int col = chosen.col();
        markAsPassage(row, col);

        int innerRow = row;
        if (row == 0) innerRow = 1;
        else if (row == grid.length - 1) innerRow = row - 1;

        int innerCol = col;
        if (col == 0) innerCol = 1;
        else if (col == grid[0].length - 1) innerCol = col - 1;

        if (grid[innerRow][innerCol].type() == WALL) {
            markAsPassage(innerRow, innerCol);
        }
        return chosen;
    }
}