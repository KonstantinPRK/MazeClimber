package app.selectors;

import app.core.MazeOptions;
import app.algorithms.catalog.SolverCatalog;
import app.algorithms.solution.Solver;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class SolverSelector implements Selector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final SolverCatalog solverCatalog;

    public SolverSelector(Terminal terminal, MazeOptions options, SolverCatalog solverCatalog) {
        this.terminal = terminal;
        this.options = options;
        this.solverCatalog = solverCatalog;
    }

    @Override
    public void setOption(int numOfLaunches) {
        if (numOfLaunches > 0) isNewOrOldOption();
        if (options.isNeedNewSolver()) setSolverOption();
    }


    public void isNewOrOldOption() {
        terminal.applyCurrentOptions(options.solver());

        if (terminal.askToNeedNewSolver()) {
            options.needNewSolver(true);
        } else {
            options.needNewSolver(false);
        }
    }


    public void setSolverOption() {
        String solverName = terminal.requestSolverOption(solverCatalog.showCatalog());
        Solver solver = solverCatalog.getAlgorithm(solverName);
        options.setSolver(solver);
    }
}