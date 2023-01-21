package edu.geekhub.homework.geometry;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public record Point(int x, int y) {
    public double distance(Point anotherPoint) {
        return sqrt(pow((double) anotherPoint.x - x, 2)
            + pow((double) anotherPoint.y - y, 2));
    }

    public Point createRespectToThis(int xDistance, int yDistance) {
        int xCoordinate = x + xDistance;
        int yCoordinate = y + yDistance;
        return new Point(xCoordinate, yCoordinate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
