package algorithms.generation;

import maze.Maze;

public interface Generator {
    Maze generate(int height, int width);

    String getName();
}
