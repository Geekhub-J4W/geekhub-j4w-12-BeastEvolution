package edu.geekhub.homework.track;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.generator.TrackElementGenerator;

import java.util.Objects;
import java.util.Set;

public final class TrackBlock {
    public static final int RATIO = 3;
    private final int x;
    private final int y;
    private final Set<TrackElement> elementBlocks;

    public TrackBlock(int x, int y) {
        this.x = x;
        this.y = y;
        elementBlocks = createBlocks(x, y);
    }

    private Set<TrackElement> createBlocks(int x, int y) {
        int centerXCoordinate = convertToBlockSystem(x);
        int centerYCoordinate = convertToBlockSystem(y);

        Point centerPoint = new Point(centerXCoordinate, centerYCoordinate);
        Point a = centerPoint.createRespectToThis(-1, -1);
        Point b = centerPoint.createRespectToThis(-1, 1);
        Point c = centerPoint.createRespectToThis(1, 1);
        Point d = centerPoint.createRespectToThis(1, -1);

        return TrackElementGenerator.generateBlockElements(a, b, c, d);
    }

    private int convertToBlockSystem(int coordinate) {
        return coordinate * RATIO;
    }

    public Set<TrackElement> getElementBlocks() {
        return elementBlocks;
    }

    public TrackBlock createRespectToThis(int xDistance, int yDistance) {
        int xCoordinate = x + xDistance;
        int yCoordinate = y + yDistance;
        return new TrackBlock(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return "TrackBlock{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackBlock that = (TrackBlock) o;
        return x == that.x && y == that.y && Objects.equals(elementBlocks, that.elementBlocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, elementBlocks);
    }
}
