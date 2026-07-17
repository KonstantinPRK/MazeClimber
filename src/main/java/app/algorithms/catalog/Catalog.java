package app.algorithms.catalog;


import java.util.List;

public interface Catalog<AlgorithmType> {
    List<String> showCatalog();
    AlgorithmType getAlgorithm(String algorithmName);
    int size();
}
