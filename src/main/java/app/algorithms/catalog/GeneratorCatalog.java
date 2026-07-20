package app.algorithms.catalog;

import app.algorithms.generation.Generator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Реализация каталога алгоритмов генерации лабиринтов.
 * Хранит генераторы, индексированные по их имени, и предоставляет
 * доступ к списку имён и получению генератора по имени.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class GeneratorCatalog implements Catalog<Generator> {
    private List<String> nameList;
    private Map<String, Generator> generatorCatalog;


    /**
     * Создаёт каталог на основе переданного списка генераторов.
     * Для каждого генератора используется его имя в качестве ключа.
     *
     * @param generators список реализаций алгоритмов генерации
     */
    public GeneratorCatalog(List<Generator> generators) {
        generatorCatalog = generators.stream()
                .collect(
                        Collectors.toMap(
                                generator -> generator.getName(),
                                generator -> generator)
                );

        nameList = List.copyOf(generatorCatalog.keySet());
    }


    /**
     * Возвращает количество алгоритмов в каталоге.
     *
     * @return число доступных генераторов
     */
    @Override
    public int size() {
        return nameList.size();
    }


    /**
     * Возвращает неизменяемый список имён всех генераторов в каталоге.
     *
     * @return список строк с именами алгоритмов
     */
    @Override
    public List<String> showCatalog() {
        return nameList;
    }


    /**
     * Возвращает генератор по его имени.
     *
     * @param algorithmName имя алгоритма генерации
     * @return генератор, соответствующий указанному имени, или {@code null},
     *         если такого имени нет в каталоге
     */
    @Override
    public Generator getAlgorithm(String algorithmName) {
        return generatorCatalog.get(algorithmName);
    }
}