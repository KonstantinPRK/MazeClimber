package app.algorithms.catalog;

import app.algorithms.generation.Generator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GeneratorCatalog implements Catalog<Generator> {
    private List<String> nameList;
    private Map<String, Generator> generatorCatalog;

    public GeneratorCatalog(List<Generator> generators){
        generatorCatalog = generators.stream()
                            .collect(
                                    Collectors.toMap(
                                    generator -> generator.getName(),
                                    generator -> generator)
                            );

        nameList = List.copyOf(generatorCatalog.keySet());
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
    public Generator getAlgorithm(String algorithmName) {
        return generatorCatalog.get(algorithmName);
    }

}
