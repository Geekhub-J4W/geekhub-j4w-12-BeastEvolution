package edu.geekhub.homework.geometrical.figures;

public enum ShapeEnum {
    CIRCLE, SQUARE, TRIANGLE, RECTANGLE;

    private static final int ORDINAL_OF_FIRST_ENUMERATION_CONSTANT = 0;


    public static int getOrdinalOfFirstEnumerationConstant() {
        return ORDINAL_OF_FIRST_ENUMERATION_CONSTANT;
    }

    public static int getOrdinalOfLastEnumerationConstant() {
        return ShapeEnum.values().length - 1;
    }
}
