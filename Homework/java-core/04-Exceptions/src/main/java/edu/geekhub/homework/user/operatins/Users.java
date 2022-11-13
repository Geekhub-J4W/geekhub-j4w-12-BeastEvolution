package edu.geekhub.homework.user.operatins;

import edu.geekhub.models.User;
import java.util.UUID;

public class Users {

    private Users() {
    }

    public static UUID[] getUserIds(User[] users) {
        UUID[] ids = new UUID[users.length];

        for (int i = 0; i < users.length; i++) {
            ids[i] = users[i].getId();
        }

        return ids;
    }

    public static String[] getUserEmails(User[] users) {
        String[] emails = new String[users.length];

        for (int i = 0; i < users.length; i++) {
            emails[i] = users[i].getEmail();
        }

        return emails;
    }

    public static String[] getUserNames(User[] users) {
        String[] names = new String[users.length];

        for (int i = 0; i < users.length; i++) {
            names[i] = users[i].getUserName();
        }

        return names;
    }

    public static String[] getFullNames(User[] users) {
        String[] fullNames = new String[users.length];

        for (int i = 0; i < users.length; i++) {
            fullNames[i] = users[i].getFullName();
        }

        return fullNames;
    }
}
