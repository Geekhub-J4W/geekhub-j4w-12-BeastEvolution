package edu.geekhub.homework.operations;

import java.util.UUID;

public class Names {
    public static boolean contains(String[] names, String key) {
        for (String name:
            names) {
            if (key.equals(name)) {
                return true;
            }
        }

        return false;
    }
}
