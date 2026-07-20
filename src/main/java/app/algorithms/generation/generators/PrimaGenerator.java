package app.algorithms.generation.generators;

import app.algorithms.generation.Generator;
import app.configuration.CurrentSize;
import app.maze.Cell;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.*;

import static app.maze.Cell.Type.*;

/**
 * Реализация алгоритма генерации лабиринта на основе алгоритма Прима
 * с последующим удалением тупиков для улучшения структуры.
 * Генерирует сбалансированные лабиринты с упорядоченным случайным распределением проходов.
 *
 * @author unknown
 * @version 1.0
 */
@Component
public class PrimaGenerator implements Generator {
    private final Random random;
    private Cell[][] grid;
    private Coordinate entrance, exit;
    private List<Cell> outerCells;


    /**
     * Создаёт генератор с заданным источником случайных чисел.
     *
     * @param random генератор случайных чисел для выбора элементов
     */
    public PrimaGenerator(Random random) {
        this.random = random;
    }


    /**
     * Возвращает имя алгоритма генерации.
     *
     * @return строка с именем и кратким описанием
     */
    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - Сбалансированный лабиринт, упорядоченный рандом.";
    }


    /**
     * Генерирует лабиринт заданного размера.
     * Создаёт сетку, строит пути по алгоритму Прима, затем добавляет вход и выход,
     * и удаляет тупики для улучшения проходимости.
     *
     * @param size размеры лабиринта (количество ячеек по высоте и ширине)
     * @return готовый объект {@link Maze} со входами и выходами
     */
    @Override
    public Maze generate(CurrentSize size) {
        initGrid(size);
        generatePaths(size);
        finalizeMaze();
        return new Maze(grid, entrance, exit);
    }


    /**
     * Инициализирует сетку лабиринта: создаёт ячейки со стенами и заполняет список внешних ячеек.
     *
     * @param size размеры лабиринта
     */
    private void initGrid(CurrentSize size) {
        int rows = 2 * size.height() + 1;
        int cols = 2 * size.width() + 1;
        grid = new Cell[rows][cols];

        outerCells = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell(new Coordinate(row, col), WALL);
                if (isOuterNonCorner(row, col)) outerCells.add(grid[row][col]);
            }
        }
    }


    /**
     * Проверяет, находится ли ячейка на внешней границе лабиринта, но не в углу.
     *
     * @param row строка ячейки
     * @param col столбец ячейки
     * @return true, если ячейка находится на границе (не в углу), иначе false
     */
    private boolean isOuterNonCorner(int row, int col) {
        int maxRow = grid.length - 1;
        int maxCol = grid[0].length - 1;

        boolean onEdge = (row == 0 || row == maxRow || col == 0 || col == maxCol);
        boolean corner = (row == 0 || row == maxRow) && (col == 0 || col == maxCol);

        return onEdge && !corner;
    }


    /**
     * Строит проходы в лабиринте по алгоритму Прима.
     * Начинает со случайной ячейки, добавляет граничные стены и последовательно их обрабатывает.
     *
     * @param size размеры лабиринта
     */
    private void generatePaths(CurrentSize size) {
        List<Coordinate> frontier = new ArrayList<>();
        Set<Coordinate> visited = new HashSet<>();

        Coordinate start = randomNode(size);
        setType(start, PASSAGE);
        visited.add(start);
        addFrontierWalls(start, frontier);

        while (!frontier.isEmpty()) {
            Coordinate wall = swapRemove(frontier, random.nextInt(frontier.size()));
            Coordinate[] nodes = nodesAroundWall(wall);
            if (nodes == null) continue;

            Coordinate firstNode = nodes[0], secondNode = nodes[1];
            boolean firstVisited = visited.contains(firstNode);
            boolean secondVisited = visited.contains(secondNode);
            if (firstVisited == secondVisited) continue;

            setType(wall, PASSAGE);
            Coordinate nextNode = firstVisited ? secondNode : firstNode;
            setType(nextNode, PASSAGE);
            visited.add(nextNode);
            addFrontierWalls(nextNode, frontier);
        }
    }


    /**
     * Выбирает случайную внутреннюю ячейку (не на границе) для старта генерации.
     *
     * @param size размеры лабиринта
     * @return координаты случайной ячейки
     */
    private Coordinate randomNode(CurrentSize size) {
        int row = 1 + 2 * random.nextInt(size.height());
        int col = 1 + 2 * random.nextInt(size.width());
        return new Coordinate(row, col);
    }


    /**
     * Добавляет стены вокруг заданной ячейки в список граничных (фронтир).
     * Добавляются стены, расположенные на расстоянии одной ячейки от узла.
     *
     * @param node     координаты ячейки, вокруг которой ищутся стены
     * @param frontier список граничных стен для пополнения
     */
    private void addFrontierWalls(Coordinate node, List<Coordinate> frontier) {
        int row = node.row(), col = node.col();

        if (row - 2 > 0) frontier.add(new Coordinate(row - 1, col));
        if (row + 2 < grid.length) frontier.add(new Coordinate(row + 1, col));

        if (col - 2 > 0) frontier.add(new Coordinate(row, col - 1));
        if (col + 2 < grid[0].length) frontier.add(new Coordinate(row, col + 1));
    }


    /**
     * Возвращает две ячейки, которые разделяет данная стена (вертикальная или горизонтальная).
     * Стена должна находиться между двумя узлами сетки.
     *
     * @param wall координаты стены
     * @return массив из двух координат узлов по обе стороны стены, или null, если стена не подходит
     */
    private Coordinate[] nodesAroundWall(Coordinate wall) {
        int row = wall.row(), col = wall.col();

        if (row % 2 == 0 && col % 2 == 1) {
            return new Coordinate[]{new Coordinate(row - 1, col), new Coordinate(row + 1, col)};
        } else if (row % 2 == 1 && col % 2 == 0) {
            return new Coordinate[]{new Coordinate(row, col - 1), new Coordinate(row, col + 1)};
        }

        return null;
    }


    /**
     * Устанавливает тип ячейки в сетке по её координатам.
     *
     * @param coord координаты ячейки
     * @param type  новый тип (стена или проход)
     */
    private void setType(Coordinate coord, Cell.Type type) {
        grid[coord.row()][coord.col()] = new Cell(coord, type);
    }


    /**
     * Возвращает тип ячейки в сетке по её координатам.
     *
     * @param coord координаты ячейки
     * @return тип ячейки (стена или проход)
     */
    private Cell.Type typeAt(Coordinate coord) {
        return grid[coord.row()][coord.col()].type();
    }


    /**
     * Удаляет элемент из списка по индексу, заменяя его последним элементом,
     * чтобы избежать сдвига (O(1) удаление).
     *
     * @param list  список координат
     * @param index индекс удаляемого элемента
     * @return удалённая координата
     */
    private Coordinate swapRemove(List<Coordinate> list, int index) {
        int lastIndex = list.size() - 1;
        Coordinate removed = list.get(index);

        list.set(index, list.get(lastIndex));
        list.remove(lastIndex);

        return removed;
    }


    /**
     * Завершает построение лабиринта: устанавливает вход и выход,
     * а затем удаляет тупики.
     */
    private void finalizeMaze() {
        setGates();
        removeDeadEnds();
    }


    /**
     * Выбирает две случайные внешние ячейки (не угловые) и соединяет их с внутренней частью лабиринта,
     * делая их входом и выходом.
     */
    private void setGates() {
        if (outerCells.size() < 2) {
            entrance = exit = new Coordinate(1, 1);
            return;
        }
        entrance = connectToInside(randomBorderCell());
        exit = connectToInside(randomBorderCell());
    }


    /**
     * Выбирает случайную ячейку из списка внешних ячеек и удаляет её из списка.
     *
     * @return координаты выбранной внешней ячейки
     */
    private Coordinate randomBorderCell() {
        int index = random.nextInt(outerCells.size());
        Cell cell = outerCells.remove(index);
        return cell.coordinate();
    }


    /**
     * Соединяет внешнюю ячейку (вход/выход) с внутренней частью лабиринта,
     * делая проход через ближайшую внутреннюю ячейку.
     *
     * @param gate координаты внешней ячейки, которая станет входом или выходом
     * @return координаты этой ячейки (вход/выход)
     */
    private Coordinate connectToInside(Coordinate gate) {
        setType(gate, PASSAGE);
        int row = gate.row();
        int col = gate.col();
        int innerRow = row, innerCol = col;
        if (row == 0) innerRow = 1;
        else if (row == grid.length - 1) innerRow = row - 1;
        if (col == 0) innerCol = 1;
        else if (col == grid[0].length - 1) innerCol = col - 1;
        Coordinate inner = new Coordinate(innerRow, innerCol);
        if (typeAt(inner) == WALL) {
            setType(inner, PASSAGE);
        }
        return gate;
    }


    /**
     * Удаляет тупики в лабиринте, превращая случайную соседнюю стену в проход,
     * чтобы улучшить связность и уменьшить количество мёртвых концов.
     */
    private void removeDeadEnds() {
        Queue<Coordinate> queue = new ArrayDeque<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                Coordinate coord = new Coordinate(row, col);
                if (isDeadEnd(coord) && !isGate(coord)) {
                    queue.add(coord);
                }
            }
        }

        while (!queue.isEmpty()) {
            Coordinate deadEnd = queue.poll();
            if (!isDeadEnd(deadEnd) || isGate(deadEnd)) continue;

            List<Coordinate> walls = nonBorderWalls(deadEnd);
            if (walls.isEmpty()) continue;

            Coordinate toOpen = walls.get(random.nextInt(walls.size()));
            setType(toOpen, PASSAGE);

            if (isDeadEnd(toOpen) && !isGate(toOpen)) {
                queue.add(toOpen);
            }
        }
    }


    /**
     * Возвращает список координат стен, соседних с данной ячейкой, которые не находятся на границе лабиринта.
     *
     * @param coord координаты ячейки
     * @return список координат стен (не граничных)
     */
    private List<Coordinate> nonBorderWalls(Coordinate coord) {
        List<Coordinate> list = new ArrayList<>();
        int row = coord.row(), col = coord.col();
        if (row > 0) addIfWall(list, row - 1, col);
        if (row < grid.length - 1) addIfWall(list, row + 1, col);
        if (col > 0) addIfWall(list, row, col - 1);
        if (col < grid[0].length - 1) addIfWall(list, row, col + 1);
        return list;
    }


    /**
     * Проверяет, является ли ячейка стеной и не находится ли она на границе лабиринта,
     * и если да, добавляет её координаты в список.
     *
     * @param list список для добавления
     * @param row  строка ячейки
     * @param col  столбец ячейки
     */
    private void addIfWall(List<Coordinate> list, int row, int col) {
        if (grid[row][col].type() == WALL && !isBorder(row, col)) {
            list.add(new Coordinate(row, col));
        }
    }


    /**
     * Проверяет, находится ли ячейка на внешней границе лабиринта.
     *
     * @param row строка ячейки
     * @param col столбец ячейки
     * @return true, если ячейка на границе, иначе false
     */
    private boolean isBorder(int row, int col) {
        return row == 0 || row == grid.length - 1 || col == 0 || col == grid[0].length - 1;
    }


    /**
     * Проверяет, является ли ячейка тупиком (проход с ровно одним соседним проходом).
     *
     * @param coord координаты ячейки
     * @return true, если ячейка — тупик, иначе false
     */
    private boolean isDeadEnd(Coordinate coord) {
        if (typeAt(coord) != PASSAGE) return false;
        int count = 0;
        int row = coord.row(), col = coord.col();
        if (row > 0 && typeAt(new Coordinate(row - 1, col)) == PASSAGE) count++;
        if (row < grid.length - 1 && typeAt(new Coordinate(row + 1, col)) == PASSAGE) count++;
        if (col > 0 && typeAt(new Coordinate(row, col - 1)) == PASSAGE) count++;
        if (col < grid[0].length - 1 && typeAt(new Coordinate(row, col + 1)) == PASSAGE) count++;
        return count == 1;
    }


    /**
     * Проверяет, является ли ячейка входом или выходом лабиринта.
     *
     * @param coord координаты ячейки
     * @return true, если ячейка — вход или выход
     */
    private boolean isGate(Coordinate coord) {
        return coord.equals(entrance) || coord.equals(exit);
    }
}