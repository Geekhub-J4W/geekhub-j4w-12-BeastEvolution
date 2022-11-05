package edu.geekhub.homework.geometrical.figures;

public enum Color {
    BLACK, RED, BLUE, GREEN, YELLOW, MAGENTA;

    private static final int ORDINAL_OF_FIRST_ENUMERATION_CONSTANT = 0;

    public static int getOrdinalOfFirstEnumerationConstant() {
        return ORDINAL_OF_FIRST_ENUMERATION_CONSTANT;
    }

    public static int getOrdinalOfLastEnumerationConstant() {
        return Color.values().length - 1;
    }
}
