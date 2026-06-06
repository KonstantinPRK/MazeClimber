package algorithms.solution;

import maze.Coordinate;
import maze.Maze;

import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

    String getName();
}
