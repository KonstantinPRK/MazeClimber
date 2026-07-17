package app.algorithms.catalog;

import app.algorithms.rendering.Renderer;
import app.algorithms.solution.Solver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SolverCatalog implements Catalog<Solver>{
    private List<String> nameList;
    private Map<String, Solver> solverCatalog;

    public SolverCatalog(List<Solver> solvers){
        solverCatalog = solvers.stream()
                .collect(
                        Collectors.toMap(
                                solver -> solver.getName(),
                                solver -> solver)
                );

        nameList = List.copyOf(solverCatalog.keySet());
    }

    @Override
    public int size(){
        return nameList.size();
    }

    @Override
    public List<String> showCatalog() {
        return nameList;
    }

    @Override
    public Solver getAlgorithm(String algorithmName) {
        return solverCatalog.get(algorithmName);
    }
}
