package algorithms.catalog;

import algorithms.solution.Solver;

import java.util.List;

public class SolverCatalog implements Catalog {

    @Override
    public List<String> showCatalog() {
        return List.of();
    }

    public Solver get(String solverName) {
        return null;
    }
}
