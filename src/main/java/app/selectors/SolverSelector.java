package app.selectors;

import app.algorithms.generation.Generator;
import app.console.Messenger;
import app.core.MazeSession;
import app.algorithms.catalog.SolverCatalog;
import app.algorithms.solution.Solver;
import org.springframework.stereotype.Component;

@Component
public class SolverSelector implements Selector {
    private final SolverCatalog solverCatalog;
    private final Messenger msg;
    private final String newSolver = "Новый способ прохождения лабиринта",
            currentSolver = "Использовать ранее выбранный алгоритм прохождения";
    private boolean isNeedNewOption = true;

    public SolverSelector(SolverCatalog solverCatalog, Messenger msg) {
        this.solverCatalog = solverCatalog;
        this.msg = msg;
    }

    @Override
    public void setOption(MazeSession session) {
        if(session.numOfLaunches() > 0) isNewOrOldOption(session);
        if(isNeedNewOption) selectOption(session);
    }

    private void isNewOrOldOption(MazeSession session){
        msg.applyCurrentOptions("Текущие параметры (алгоритм поиска решения)", ": ", session.solver().getName());
        msg.showInformation("Каким способом вы желаете найти выход из лабиринта?");
        isNeedNewOption = msg.requestRespond(newSolver, currentSolver).equals(currentSolver);
    }

    private void selectOption(MazeSession session){
        msg.showInformation("Выберите алгоритм поиска решения. ");
        String solverName = msg.requestRespond(solverCatalog.showCatalog());
        Solver solver = solverCatalog.getAlgorithm(solverName);
        session.setSolver(solver);
    }
}