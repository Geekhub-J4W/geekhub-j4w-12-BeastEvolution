package edu.geekhub.homework.geometry;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PointTest {
    @Test
    @Tag("Correct")
    void Measure_distance_between_points() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(2,-2);
        Point p3 = new Point(2,0);
        Point p4 = new Point(0, 2);

        double actualResult = p1.distance(p2);
        double actualResult1 = p3.distance(p4);
        System.out.println(actualResult);
        System.out.println(actualResult1);
        System.out.println(actualResult == actualResult1);
        System.out.println(p1.distance(p3));
    }

    @Test
    void Points_are_the_same_if_their_fields_are_the_same() {

    }
}
