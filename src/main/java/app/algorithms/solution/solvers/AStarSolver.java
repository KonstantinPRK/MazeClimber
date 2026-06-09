package app.algorithms.solution.solvers;

import app.algorithms.solution.Solver;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AStarSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        return List.of();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - поиск кратчайшего пути.";
    }
    //название класса потом поменяю
}
