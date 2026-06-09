package app.algorithms.rendering.renderers;

import app.algorithms.rendering.Renderer;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LineRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        return "";
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return "";
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - линейный отрисовщик";
    }

}
