package app.algorithms.rendering;

import app.maze.Coordinate;
import app.maze.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);
    String render(Maze maze, List<Coordinate> path);
    String getName();
}

