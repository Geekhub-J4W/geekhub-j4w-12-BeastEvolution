package edu.geekhub.homework.geometrical.figures;

public class Triangle extends ShapeImp {

    private static final int NUMBER_OF_SIDES = 3;

    public static final String NAME = Triangle.class.getSimpleName();
    private final int sideA;
    private final int sideB;
    private final int sideC;

    public Triangle() {
        super();
        int[] sidesOfTriangle = getSidesOfTriangleFromUser();
        sideA = sidesOfTriangle[0];
        sideB = sidesOfTriangle[1];
        sideC = sidesOfTriangle[2];
        setPerimeter(calculatePerimeter());
        setArea(calculateArea());
    }

    private int[] getSidesOfTriangleFromUser() {
        int[] sidesOfTriangle = new int[NUMBER_OF_SIDES];
        sidesOfTriangle[0] = getCharacteristicOfFigureFromUser("side A");
        sidesOfTriangle[1] = getCharacteristicOfFigureFromUser("side B");
        sidesOfTriangle[2] = getCharacteristicOfFigureFromUser("side C");
        while (!validateSidesOfTriangle(sidesOfTriangle)) {
            System.out.println("The sum of two side lengths has to exceed the length of the third side.");
            System.out.println("Try again.");

            sidesOfTriangle[0] = getCharacteristicOfFigureFromUser("side A");
            sidesOfTriangle[1] = getCharacteristicOfFigureFromUser("side B");
            sidesOfTriangle[2] = getCharacteristicOfFigureFromUser("side C");
        }

        return sidesOfTriangle;
    }

    private boolean validateSidesOfTriangle(int[] sidesOfTriangle) {
        if (sidesOfTriangle[0] + sidesOfTriangle[1] <= sidesOfTriangle[2]) {
            return false;
        } else if (sidesOfTriangle[1] + sidesOfTriangle[2] <= sidesOfTriangle[0]) {
            return false;
        } else if (sidesOfTriangle[2] + sidesOfTriangle[0] <= sidesOfTriangle[1]) {
            return false;
        }

        return true;
    }

    public int getSideA() {
        return sideA;
    }

    public int getSideB() {
        return sideB;
    }

    public int getSideC() {
        return sideC;
    }

    @Override
    public double calculatePerimeter() {
        return (double) sideA + sideB + sideC;
    }

    @Override
    public double calculateArea() {
        double semiperimeter = calculatePerimeter() / 2;
        return Math.sqrt(semiperimeter * (semiperimeter - sideA)
            * (semiperimeter - sideB) * (semiperimeter - sideC));
    }

    @Override
    protected void printName() {
        System.out.println("Shape type: " + NAME);
    }
}
