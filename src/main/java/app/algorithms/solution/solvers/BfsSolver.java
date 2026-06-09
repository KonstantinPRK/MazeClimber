package app.algorithms.solution.solvers;

import app.algorithms.solution.Solver;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BfsSolver implements Solver {

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        return List.of();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - веерный поиск, перебор множества вариантов.";
    }
    //название класса потом поменяю
}
