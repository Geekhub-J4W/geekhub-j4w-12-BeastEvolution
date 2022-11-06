package edu.geekhub.homework.geometrical.figures;

public class Rectangle extends ShapeImp {

    public static final String NAME = Rectangle.class.getSimpleName();
    private static final int NUMBER_OF_SIDES_LENGTH = 2;
    private static final int NUMBER_OF_SIDES_WIDTH = 2;

    private final double length;
    private final double width;

    public Rectangle() {
        length = getCharacteristicOfFigureFromUser("length");
        width = getCharacteristicOfFigureFromUser("width");
        setPerimeter(calculatePerimeter());
        setArea(calculateArea());
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double calculatePerimeter() {
        return length * NUMBER_OF_SIDES_LENGTH + width * NUMBER_OF_SIDES_WIDTH;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    protected void printName() {
        System.out.println("Shape type: " + NAME);
    }
}
