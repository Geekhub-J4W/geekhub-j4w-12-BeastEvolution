package edu.geekhub.homework.track.generator;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.TrackElement;

import java.util.HashSet;
import java.util.Set;

public final class TrackElementGenerator {

    private TrackElementGenerator() {
    }

    public static Set<TrackElement> generateBlockElements(Point a, Point b, Point c, Point d) {
        validateCoordinates(a, b, c, d);
        return createBlockElements(a, b, c, d);
    }

    private static void validateCoordinates(Point a, Point b, Point c, Point d) {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double cd = c.distance(d);
        double da = d.distance(a);
        if (ab != bc) {
            throw new IllegalArgumentException(
                "Sides AB not equal BC"
            );
        } else if (bc != cd) {
            throw new IllegalArgumentException(
                "Sides BC not equal CD"
            );
        } else if (cd != da) {
            throw new IllegalArgumentException(
                "Sides CD not equal DA"
            );
        }
    }
    private static Set<TrackElement> createBlockElements(Point a, Point b, Point c, Point d) {
        if(b.y() != c.y()) {
            throw new IllegalArgumentException(
                String.format("Coordinate X of point B and A must be the same, but: %s, %s", b.y(), c.y())
            );
        }
        Set<TrackElement> areaElements = new HashSet<>();
        for (int y = b.y(); y >= a.y(); y--) {
            areaElements.addAll(createRow(new Point(b.x(), y), new Point(c.x(), y)));
        }

        return areaElements;
    }

    private static Set<TrackElement> createRow(Point b, Point c) {
        if(b.y() != c.y()) {
            throw new IllegalArgumentException(
                String.format("Coordinate Y of point B and C must be the same, but: %s, %s", b.y(), c.y())
            );
        }
        Set<TrackElement> rowElements = new HashSet<>();
        for (int x = b.x(); x <= c.x(); x++) {
            rowElements.add(
                new TrackElement(new Point(x, b.y()))
            );
        }

        return rowElements;
    }
}
