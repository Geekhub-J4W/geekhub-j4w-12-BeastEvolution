package edu.geekhub.homework.user.operatins;

public class FullNames {

    private FullNames() {
    }

    public static boolean contains(String[] fullNames, String key) {
        for (String name : fullNames) {
            if (key.equals(name)) {
                return true;
            }
        }

        return false;
    }
}
