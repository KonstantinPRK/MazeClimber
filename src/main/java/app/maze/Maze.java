package app.maze;

import app.configuration.CurrentSize;

import java.util.Arrays;

public final class Maze {
    private final Cell[][] grid;
    private final Coordinate entrance, exit;

    public Maze(Cell[][] grid, Coordinate entrance, Coordinate exit) {
        this.grid = grid;
        this.entrance = entrance;
        this.exit = exit;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public CurrentSize size(){
        return new CurrentSize(grid.length, grid[0].length);
    }

    public Cell[][] getGrid() {
        return Arrays.stream(this.grid)
                .map(Cell[]::clone)
                .toArray(Cell[][]::new);
    }

    //для солверов
    public Coordinate getEntrance(){
        return entrance;
    }
    public Coordinate getExit(){
        return exit;
    }

}