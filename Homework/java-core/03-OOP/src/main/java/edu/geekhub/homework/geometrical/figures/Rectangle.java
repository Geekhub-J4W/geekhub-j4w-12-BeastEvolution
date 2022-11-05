package edu.geekhub.homework.geometrical.figures;

public class Rectangle extends ShapeImp {

    public static final String NAME = Rectangle.class.getSimpleName();
    private static final int NUMBER_OF_SIDES_LENGTH = 2;
    private static final int NUMBER_OF_SIDES_WIDTH = 2;

    private final int length;
    private final int width;

    public Rectangle() {
        length = getCharacteristicOfFigureFromUser("length");
        width = getCharacteristicOfFigureFromUser("width");
        setPerimeter(calculatePerimeter());
        setArea(calculateArea());
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public double calculatePerimeter() {
        return (double) length * NUMBER_OF_SIDES_LENGTH + width * NUMBER_OF_SIDES_WIDTH;
    }

    @Override
    public double calculateArea() {
        return (double) length * width;
    }

    @Override
    protected void printName() {
        System.out.println("Shape type: " + NAME);
    }
}
