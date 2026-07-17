package app.core;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.configuration.CurrentSize;


public class MazeSession {
    private int numOfLaunches = 0;
    private CurrentSize size;
    private Generator generator;
    private Solver solver;
    private Renderer renderer;


    public int numOfLaunches(){return numOfLaunches;}
    public void newLaunch() {numOfLaunches++;}


    //геттеры и сеттеры размеров
    public CurrentSize size(){return size; }
    public void setSize(CurrentSize size){this.size = size;}


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
