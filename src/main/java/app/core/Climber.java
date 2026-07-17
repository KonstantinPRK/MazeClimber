package app.core;

import app.algorithms.generation.Generator;
import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import app.console.Messenger;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Climber {
    private final Messenger msg;
    private List<Coordinate> path;
    private Maze maze;

    private String CONTINUE = "Да",
                   STOP = "Нет. (программа завершится)";


    public Climber(Messenger messenger){
        this.msg = messenger;
    }

    protected void sayHello(){
        msg.showInformation( "В этой программе возможно сгенерировать случайный лабиринт, выбрать способ поиска решения и увидеть историю его прохождения.",
                "Чтобы управлять программой используйте числа.",
                "Для генерации лабиринта выберите необходимые параметры: ");
    }

    protected void createNewMaze(MazeSession session) {
            Generator generator = session.generator();
            maze = generator.generate(session.size());
    }

    protected void solveMaze(MazeSession session){
        Solver solver = session.solver();
        path = solver.solve(maze);
    }

    protected void toRenderMazeSolution(MazeSession session) {
        Renderer renderer = session.renderer();
        String mazeDraw = renderer.render(maze),
               solvedMazeDraw = renderer.render(maze, path);

        msg.unformattedPrint(mazeDraw, solvedMazeDraw);
    }

    public boolean isContinue(MazeSession session) {
        msg.showInformation("Вы хотите продолжить генерацию и прохождение лабиринтов ?");
        boolean userRespond = msg.requestRespond(CONTINUE, STOP).equals(CONTINUE);
        if(userRespond) session.newLaunch();
        return userRespond;
    }
}
