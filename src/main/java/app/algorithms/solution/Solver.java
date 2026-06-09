package app.algorithms.solution;

import app.maze.Coordinate;
import app.maze.Maze;

import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
    String getName();
}
