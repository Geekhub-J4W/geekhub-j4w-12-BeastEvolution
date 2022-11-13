package edu.geekhub.homework.user.operatins;

public class UserNames {
    private UserNames() {
    }

    public static boolean contains(String[] names, String key) {
        for (String name : names) {
            if (key.equals(name)) {
                return true;
            }
        }

        return false;
    }
}
