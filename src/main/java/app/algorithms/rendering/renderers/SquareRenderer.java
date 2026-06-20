package app.algorithms.rendering.renderers;

import app.algorithms.rendering.Renderer;
import app.maze.Cell;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static app.maze.Cell.Type.*;

@Component
public class SquareRenderer implements Renderer {
    private final String wallDraw  = "██";
    private final String emptyDraw = "░░";
    private final String pathDraw  = "●░";

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - отрисовка квадратами";
    }

    @Override
    public String render(Maze maze) {
        return render(maze, Collections.emptyList());
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        Cell[][] grid = maze.getGrid();

        Set<Coordinate> pathSet = (path != null && !path.isEmpty()) ? new HashSet<>(path) : Collections.emptySet();
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < maze.size().height(); row++) {
            for (int col = 0; col < maze.size().width(); col++) {
                Coordinate coord = new Coordinate(row, col);

                if (grid[row][col].type() == WALL) {
                    builder.append(wallDraw);
                } else if (pathSet.contains(coord)) {
                    builder.append(pathDraw);
                } else {
                    builder.append(emptyDraw);
                }
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}