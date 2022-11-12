package edu.geekhub.homework.operations;

import java.util.UUID;

public class Emails {
    public static boolean contains(String[] emails, String key) {
        for (String email:
            emails) {
            if (key.equals(email)) {
                return true;
            }
        }

        return false;
    }
}
