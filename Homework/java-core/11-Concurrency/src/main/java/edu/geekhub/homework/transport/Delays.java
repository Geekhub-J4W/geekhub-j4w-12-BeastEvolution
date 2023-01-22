package edu.geekhub.homework.transport;

import java.util.concurrent.ThreadLocalRandom;

public class Delays {
    private Delays() {
    }

    public static long getRandomDelayInMillis(long origin, long bound) {
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }
}
