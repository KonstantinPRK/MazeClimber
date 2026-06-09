package app.algorithms.catalog;

import app.algorithms.generation.Generator;

import java.util.List;

public interface Catalog<AlgorithmType> {
    List<String> showCatalog();
    AlgorithmType getAlgorithm(String algorithmName);
}
