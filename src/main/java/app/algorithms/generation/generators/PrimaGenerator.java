package app.algorithms.generation.generators;

import app.algorithms.generation.Generator;
import app.maze.Maze;
import org.springframework.stereotype.Component;

@Component
public class PrimaGenerator implements Generator {

    //упорядоченный рандом
    @Override
    public Maze generate(int height, int width) {
        return null;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - Сбалансированный лабиринт, упорядоченный рандом.";
    }

    /*

    коллекция неиспользованных координат
    определяем точку для рисования
    рисуем фигуру и заполняем пустоты вокруг
    убираем координаты и пустоты
     */

}

