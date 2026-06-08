package app.algorithms.generation;

import app.maze.Maze;

public interface Generator {
    Maze generate(int height, int width);

    String getName();
}
