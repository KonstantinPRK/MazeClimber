package app.algorithms.generation.generators;

import app.algorithms.generation.Generator;
import app.configuration.CurrentSize;
import app.maze.Cell;
import app.maze.Coordinate;
import app.maze.Maze;
import org.springframework.stereotype.Component;

import java.util.*;

import static app.maze.Cell.Type.*;

@Component
public class PrimaGenerator implements Generator {
    private final Random random;
    private Cell[][] grid;
    private Coordinate entrance, exit;
    private List<Cell> outerCells;

    public PrimaGenerator(Random random) {
        this.random = random;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName() + " - Сбалансированный лабиринт, упорядоченный рандом.";
    }


    @Override
    public Maze generate(CurrentSize size) {
        initGrid(size);
        generatePaths(size);
        finalizeMaze();
        return new Maze(grid, entrance, exit);
    }

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

    private boolean isOuterNonCorner(int row, int col) {
        int maxRow = grid.length - 1;
        int maxCol = grid[0].length - 1;

        boolean onEdge = (row == 0 || row == maxRow || col == 0 || col == maxCol);
        boolean corner = (row == 0 || row == maxRow) && (col == 0 || col == maxCol);

        return onEdge && !corner;
    }

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

    private Coordinate randomNode(CurrentSize size) {
        int row = 1 + 2 * random.nextInt(size.height());
        int col = 1 + 2 * random.nextInt(size.width());
        return new Coordinate(row, col);
    }

    private void addFrontierWalls(Coordinate node, List<Coordinate> frontier) {
        int row = node.row(), col = node.col();

        if (row - 2 > 0) frontier.add(new Coordinate(row - 1, col));
        if (row + 2 < grid.length) frontier.add(new Coordinate(row + 1, col));

        if (col - 2 > 0) frontier.add(new Coordinate(row, col - 1));
        if (col + 2 < grid[0].length) frontier.add(new Coordinate(row, col + 1));
    }

    private Coordinate[] nodesAroundWall(Coordinate wall) {
        int row = wall.row(), col = wall.col();

        if (row % 2 == 0 && col % 2 == 1) {
            return new Coordinate[]{ new Coordinate(row - 1, col), new Coordinate(row + 1, col) };
        } else if (row % 2 == 1 && col % 2 == 0) {
            return new Coordinate[]{ new Coordinate(row, col - 1), new Coordinate(row, col + 1) };
        }

        return null;
    }

    private void setType(Coordinate coord, Cell.Type type) {
        grid[coord.row()][coord.col()] = new Cell(coord, type);
    }

    private Cell.Type typeAt(Coordinate coord) {
        return grid[coord.row()][coord.col()].type();
    }

    private Coordinate swapRemove(List<Coordinate> list, int index) {
        int lastIndex = list.size() - 1;
        Coordinate removed = list.get(index);

        list.set(index, list.get(lastIndex));
        list.remove(lastIndex);

        return removed;
    }

    private void finalizeMaze() {
        setGates();
        removeDeadEnds();
    }

    private void setGates() {
        if (outerCells.size() < 2) {
            entrance = exit = new Coordinate(1, 1);
            return;
        }
        entrance = connectToInside(randomBorderCell());
        exit = connectToInside(randomBorderCell());
    }

    private Coordinate randomBorderCell() {
        int index = random.nextInt(outerCells.size());
        Cell cell = outerCells.remove(index);
        return cell.coordinate();
    }

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

    private List<Coordinate> nonBorderWalls(Coordinate coord) {
        List<Coordinate> list = new ArrayList<>();
        int row = coord.row(), col = coord.col();
        if (row > 0) addIfWall(list, row - 1, col);
        if (row < grid.length - 1) addIfWall(list, row + 1, col);
        if (col > 0) addIfWall(list, row, col - 1);
        if (col < grid[0].length - 1) addIfWall(list, row, col + 1);
        return list;
    }

    private void addIfWall(List<Coordinate> list, int row, int col) {
        if (grid[row][col].type() == WALL && !isBorder(row, col)) {
            list.add(new Coordinate(row, col));
        }
    }

    private boolean isBorder(int row, int col) {
        return row == 0 || row == grid.length - 1 || col == 0 || col == grid[0].length - 1;
    }

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

    private boolean isGate(Coordinate coord) {
        return coord.equals(entrance) || coord.equals(exit);
    }
}