package app.core;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.configuration.CurrentSize;
import org.springframework.stereotype.Component;

@Component
public class MazeOptions {
    private Boolean
            needNewSize = true,
            needNewGenerator = true,
            needNewSolver = true,
            needNewRenderer = true;

    private CurrentSize size;
    private Generator generator;
    private Solver solver;
    private Renderer renderer;

    //геттеры и сеттеры условий
    public boolean isNeedNewSize() {
        return needNewSize;
    }
    public void needNewSize(boolean needNewMaze) {
        this.needNewSize = needNewMaze;
    }

    public boolean isNeedNewGenerator() {return needNewGenerator;}
    public void needNewGenerator(boolean needNewGenerator) {this.needNewGenerator = needNewGenerator;}

    public boolean isNeedNewSolver() {
        return needNewSolver;
    }
    public void needNewSolver(boolean needNewSolver) {
        this.needNewSolver = needNewSolver;
    }

    public boolean isNeedNewRenderer() {
        return needNewRenderer;
    }
    public void needNewRenderer(boolean needNewRenderer) {
        this.needNewRenderer = needNewRenderer;
    }


    //геттеры и сеттеры размеров
    public int height() {
        return size.height();
    }
    public int width() {
        return size.width();
    }
    public void setNewSize(CurrentSize size){this.size = size;}


    //геттеры и сеттеры алгоритмов
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
}
