package edu.geekhub.homework.geometrical.figures;

public interface Shape {
    double calculateArea();

    double calculatePerimeter();

    void printInfo();

    void compare(ShapeImp anotherFigure);
}
