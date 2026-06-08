package app.selectors;

import app.core.MazeOptions;
import app.algorithms.catalog.SolverCatalog;
import app.algorithms.solution.Solver;
import app.console.Terminal;
import org.springframework.stereotype.Component;

@Component
public class SolverSelector {
    private final Terminal terminal;
    private final MazeOptions options;
    private final SolverCatalog solverCatalog;

    public SolverSelector(Terminal terminal, MazeOptions options, SolverCatalog solverCatalog) {
        this.terminal = terminal;
        this.options = options;
        this.solverCatalog = solverCatalog;
    }


    public void isNewOrOldSolver() {
        terminal.applyCurrentOptions(options.solver());

        if (terminal.askToNeedNewSolver()) {
            options.needNewSolver(true);
        } else {
            options.needNewSolver(false);
        }
    }


    public void changeSolver() {
        String solverName = terminal.requestSolverOption(solverCatalog.showCatalog());
        Solver solver = solverCatalog.get(solverName);
        options.setSolver(solver);
    }
}