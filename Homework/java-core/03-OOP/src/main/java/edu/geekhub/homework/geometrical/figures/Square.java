package edu.geekhub.homework.geometrical.figures;

public class Square extends ShapeImp {
    private static final int NUMBER_OF_SIDES = 4;

    public static final String NAME = Square.class.getSimpleName();

    private final int side;

    public Square() {
        super();
        side = getCharacteristicOfFigureFromUser("side");
        setPerimeter(calculatePerimeter());
        setArea(calculateArea());
    }

    public int getSide() {
        return side;
    }

    @Override
    public double calculatePerimeter() {
        return (double) side * NUMBER_OF_SIDES;
    }

    @Override
    public double calculateArea() {
        return Math.pow(side, 2);
    }

    @Override
    protected void printName() {
        System.out.println("Shape type: " + NAME);
    }
}
