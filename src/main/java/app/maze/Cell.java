package app.maze;


public record Cell(Coordinate coordinate, Type type) {
    public enum Type { WALL, PASSAGE }
}