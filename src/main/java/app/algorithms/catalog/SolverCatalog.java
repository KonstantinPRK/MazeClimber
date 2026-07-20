package app.algorithms.catalog;

import app.algorithms.solution.Solver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Реализация каталога алгоритмов поиска пути (солверов) в лабиринте.
 * Хранит солверы, индексированные по их имени, и предоставляет
 * доступ к списку имён и получению солвера по имени.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class SolverCatalog implements Catalog<Solver> {
    private List<String> nameList;
    private Map<String, Solver> solverCatalog;


    /**
     * Создаёт каталог на основе переданного списка солверов.
     * Для каждого солвера используется его имя в качестве ключа.
     *
     * @param solvers список реализаций алгоритмов поиска пути
     */
    public SolverCatalog(List<Solver> solvers) {
        solverCatalog = solvers.stream()
                .collect(
                        Collectors.toMap(
                                solver -> solver.getName(),
                                solver -> solver)
                );

        nameList = List.copyOf(solverCatalog.keySet());
    }


    /**
     * Возвращает количество алгоритмов в каталоге.
     *
     * @return число доступных солверов
     */
    @Override
    public int size() {
        return nameList.size();
    }


    /**
     * Возвращает неизменяемый список имён всех солверов в каталоге.
     *
     * @return список строк с именами алгоритмов
     */
    @Override
    public List<String> showCatalog() {
        return nameList;
    }


    /**
     * Возвращает солвер по его имени.
     *
     * @param algorithmName имя алгоритма поиска пути
     * @return солвер, соответствующий указанному имени, или {@code null},
     *         если такого имени нет в каталоге
     */
    @Override
    public Solver getAlgorithm(String algorithmName) {
        return solverCatalog.get(algorithmName);
    }
}