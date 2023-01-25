package edu.geekhub.homework.transport.util;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(int ratio) {
        return x * ratio;
    }

    public int getY(int ratio) {
        return y * ratio;
    }

    public static Direction randomDirection() {
        int pick = ThreadLocalRandom.current().nextInt(values().length);
        return Direction.values()[pick];
    }
}
