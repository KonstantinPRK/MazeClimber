package App.configuration;

import algorithms.generation.Generator;
import algorithms.rendering.Renderer;
import algorithms.solution.Solver;

public class MazeOptions {
    private Boolean needNewMaze = true, needNewSolver = true, needNewRenderer;
    private Integer height, width;
    private Generator generator;
    private Solver solver;
    private Renderer renderer;

    public boolean isNeedNewMaze() {
        return needNewMaze;
    }

    public void needNewMaze(boolean needNewMaze) {
        this.needNewMaze = needNewMaze;
    }


    public boolean isNeedNewSolver() {
        return needNewSolver;
    }

    public void needNewSolver(boolean needNewSolver) {
        this.needNewSolver = needNewSolver;
    }


    public int height() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int width() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Generator generator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public Solver solver() {
        return solver;
    }

    public void setSolver(Solver solver) {
        this.solver = solver;
    }


    public Renderer renderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public Boolean isNeedNewRenderer() {
        return needNewRenderer;
    }

    public void needNewRenderer(Boolean needNewRenderer) {
        this.needNewRenderer = needNewRenderer;
    }
}
