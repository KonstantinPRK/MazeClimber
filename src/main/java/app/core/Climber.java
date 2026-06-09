package app.core;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.console.Terminal;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Climber {
    protected MazeOptions options;
    private final Terminal terminal;

    protected List<Coordinate> path;
    private Maze maze;


    public Climber(MazeOptions options, Terminal terminal){
        this.options = options;
        this.terminal = terminal;
    }

    protected void sayHello(){
        terminal.sayHello();
    }

    protected void createNewMaze() {
            Generator generator = options.generator();
            maze = generator.generate(options.height(), options.width());
    }

    protected void solveMaze(){
        Solver solver = options.solver();
        path = solver.solve(maze, null, null);
    }

    protected void toRenderMazeSolution() {
        Renderer renderer = options.renderer();
        renderer.render(maze, path);
    }

    public boolean isContinue() {
        return terminal.askContinueOption();
    }
}
