package app.algorithms.catalog;

import app.algorithms.generation.Generator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneratorCatalog {


    public List<String> showCatalog() {
        return List.of();
    }


    public Generator get(String algorithmName) {
        return null;
    }
}
