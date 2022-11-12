package edu.geekhub.homework.operations;

public class FullNames {
    public static boolean contains(String[] fullNames, String key) {
        for (String name:
            fullNames) {
            if (key.equals(name)) {
                return true;
            }
        }

        return false;
    }
}
