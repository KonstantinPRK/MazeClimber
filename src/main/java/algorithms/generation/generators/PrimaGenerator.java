package algorithms.generation.generators;

import algorithms.generation.Generator;
import maze.Cell;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class PrimaGenerator implements Generator {
    Map<Integer, Set<Integer>> rowsCols;
    Cell[][] grid;
    Random random;
    int height, width;

    //упорядоченный рандом
    @Override
    public Cell[][] generate(int height, int width) {
        initialize(height, width);
        toFillGrid();

        toFrameEdges();

        toMapGates();
        return null;
    }

    /*

    коллекция неиспользованных координат
    определяем точку для рисования
    рисуем фигуру и заполняем пустоты вокруг
    убираем координаты и пустоты
     */


    private void initialize(int height, int width){
        this.grid = new Cell[height][width];
        this.random = new Random();
        this.height = height;
        this.width = width;
    }

    private void toFillGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = createNewCell(row, col);
            }
        }
    }

    public Cell createNewCell(int row, int col) {
        int wallcount = 0;


        return null;
    }

    private boolean isEdge(int row, int col){
        return true;
    }

    private void toFrameEdges(){

    }

    private void toMapGates() {

    }




}

