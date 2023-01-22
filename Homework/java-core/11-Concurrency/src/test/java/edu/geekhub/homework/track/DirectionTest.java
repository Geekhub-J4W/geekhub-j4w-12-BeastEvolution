package edu.geekhub.homework.track;

import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void getRandomDirection() {
        Direction direction = Direction.randomDirection();
        System.out.println(direction);
        System.out.println(direction.getX(5));
        System.out.println(direction.getY(5));
    }

}
