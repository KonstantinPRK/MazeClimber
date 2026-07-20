package app.algorithms.catalog;

import app.algorithms.rendering.Renderer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Реализация каталога алгоритмов отрисовки лабиринтов.
 * Хранит рендереры, индексированные по их имени, и предоставляет
 * доступ к списку имён и получению рендерера по имени.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class RendererCatalog implements Catalog<Renderer> {
    private List<String> nameList;
    private Map<String, Renderer> rendererCatalog;


    /**
     * Создаёт каталог на основе переданного списка рендереров.
     * Для каждого рендерера используется его имя в качестве ключа.
     *
     * @param renderers список реализаций алгоритмов отрисовки
     */
    public RendererCatalog(List<Renderer> renderers) {
        rendererCatalog = renderers.stream()
                .collect(
                        Collectors.toMap(
                                renderer -> renderer.getName(),
                                renderer -> renderer)
                );

        nameList = List.copyOf(rendererCatalog.keySet());
    }


    /**
     * Возвращает количество алгоритмов в каталоге.
     *
     * @return число доступных рендереров
     */
    @Override
    public int size() {
        return nameList.size();
    }


    /**
     * Возвращает неизменяемый список имён всех рендереров в каталоге.
     *
     * @return список строк с именами алгоритмов
     */
    @Override
    public List<String> showCatalog() {
        return nameList;
    }


    /**
     * Возвращает рендерер по его имени.
     *
     * @param algorithmName имя алгоритма отрисовки
     * @return рендерер, соответствующий указанному имени, или {@code null},
     *         если такого имени нет в каталоге
     */
    @Override
    public Renderer getAlgorithm(String algorithmName) {
        return rendererCatalog.get(algorithmName);
    }
}