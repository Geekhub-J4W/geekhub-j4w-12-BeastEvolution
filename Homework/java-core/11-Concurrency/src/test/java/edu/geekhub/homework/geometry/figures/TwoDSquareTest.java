package edu.geekhub.homework.geometry.figures;

import edu.geekhub.homework.geometry.Point;
import edu.geekhub.homework.track.TrackElement;
import edu.geekhub.homework.track.generator.TrackElementGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TwoDSquareTest {
    @Test
    @Tag("Correct")
    void Create_2D_square() {
        Point a = new Point(0, -2);
        Point b = new Point(0, 0);
        Point c = new Point(2, 0);
        Point d = new Point(2, -2);

        Set<TrackElement> result = TrackElementGenerator.generateBlockElements(a, b, c, d);
        assertThat(result)
            .hasSize(9);
    }

    @Test
    @Tag("Exception")
    void Invalid_to_create_2D_square_with_unequal_sides() {
        Point a = new Point(0, -2);
        Point b = new Point(0, 0);
        Point c = new Point(3, 0);
        Point d = new Point(3, -2);

        assertThatThrownBy(() -> TrackElementGenerator.generateBlockElements(a, b, c, d))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Sides AB not equal BC");
    }
}
