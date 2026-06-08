package app.algorithms.catalog;

import app.algorithms.solution.Solver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SolverCatalog {

    public List<String> showCatalog() {
        return List.of();
    }

    public Solver get(String solverName) {
        return null;
    }
}
