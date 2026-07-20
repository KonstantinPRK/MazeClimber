package app.configuration;

/**
 * Запись, представляющая размеры лабиринта в виде высоты и ширины.
 *
 * @param height высота лабиринта (количество строк)
 * @param width  ширина лабиринта (количество столбцов)
 */
public record CurrentSize(int height, int width) { }