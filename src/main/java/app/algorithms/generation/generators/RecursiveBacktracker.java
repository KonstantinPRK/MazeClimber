package app.algorithms.generation.generators;

import app.algorithms.generation.Generator;
import app.maze.Maze;
import org.springframework.stereotype.Component;

@Component
public class RecursiveBacktracker implements Generator {
    @Override
    public Maze generate(int height, int width) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }
}
