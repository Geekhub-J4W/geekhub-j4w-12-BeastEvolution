package edu.geekhub.homework.operations;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Ids {
    public static boolean contains(UUID[] ids, UUID key) {
        for (UUID id:
            ids) {
            if (key.equals(id)) {
                return true;
            }
        }

        return false;
    }
}
