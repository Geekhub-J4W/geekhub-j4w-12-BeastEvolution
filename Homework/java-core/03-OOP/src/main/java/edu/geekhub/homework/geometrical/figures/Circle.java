package edu.geekhub.homework.geometrical.figures;

public class Circle extends ShapeImp {

    public static final String NAME = Circle.class.getSimpleName();
    private final double radius;
    private final double diameter;

    public Circle() {
        super();
        this.radius = getCharacteristicOfFigureFromUser("radius");
        diameter = calculateDiameter();
        setPerimeter(calculatePerimeter());
        setArea(calculateArea());
    }

    @Override
    public String getName() {
        return NAME;
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return diameter;
    }

    private double calculateDiameter() {
        return 2 * radius;
    }

    @Override
    public double calculatePerimeter() {
        return diameter * Math.PI;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    protected void printName() {
        System.out.println("Shape type: " + NAME);
    }
}
