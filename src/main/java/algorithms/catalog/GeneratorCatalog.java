package algorithms.catalog;

import algorithms.generation.Generator;

import java.util.List;

public class GeneratorCatalog implements Catalog {

    @Override
    public List<String> showCatalog() {
        return List.of();
    }


    public Generator get(String algorithmName) {
        return null;
    }
}
