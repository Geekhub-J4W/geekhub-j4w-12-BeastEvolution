package edu.geekhub.homework.user.operatins;

import java.util.UUID;

public class Ids {

    private Ids() {
    }

    public static boolean contains(UUID[] ids, UUID key) {
        for (UUID id : ids) {
            if (key.equals(id)) {
                return true;
            }
        }

        return false;
    }
}
