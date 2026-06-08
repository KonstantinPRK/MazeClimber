package app.core;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Climber {
    protected MazeOptions options;

    protected List<Coordinate> path;
    private Maze maze;


    public Climber(MazeOptions options){
        this.options = options;
    }


    protected void createNewMaze() {
        if(options.isNeedNewMaze()){
            Generator generator = options.generator();
            maze = generator.generate(options.height(), options.width());
        }
    }

    protected void solveMaze(){
        Solver solver = options.solver();
        path = solver.solve(maze, null, null);
    }

    protected void toRenderMazeSolution() {
        Renderer renderer = options.renderer();
        renderer.render(maze, path);
    }
}
