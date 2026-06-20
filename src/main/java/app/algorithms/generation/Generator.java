package app.algorithms.generation;

import app.configuration.CurrentSize;
import app.maze.Maze;

public interface Generator {
    Maze generate(CurrentSize size);
    String getName();
}
