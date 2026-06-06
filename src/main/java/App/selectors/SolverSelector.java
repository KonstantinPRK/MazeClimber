package App.selectors;
import algorithms.catalog.*;
import algorithms.solution.*;

public class SolverSelector extends Selector {
    private SolverCatalog solverCatalog;

    public SolverSelector(SolverCatalog solverCatalog) {
        this.solverCatalog = solverCatalog;
    }


    protected void isNewOrOldSolver() {
        terminal.applyCurrentOptions(mazeOptions.solver());

        if(terminal.askToNeedNewSolver()){
            mazeOptions.needNewMaze(true);
        } else {
            mazeOptions.needNewMaze(false);
        }
    }


    protected void changeSolver() {
        String solverName = terminal.requestSolverOption(solverCatalog.showCatalog());
        Solver solver = solverCatalog.get(solverName);
        mazeOptions.setSolver(solver);
    }


    protected void solveMaze(){
        Solver solver = mazeOptions.solver();
        path = solver.solve(maze, null, null);
    }
}
