package com.sbertex;

public class PaintExample {
    public static void main(String[] args) {
        Figure circle = new Circle(new Point(0.0f, 0.0f), 10.0f);
        Figure rect = new Rectangle(new Point(0.0f, 0.0f), new Point(10.0f, 20.0f));
        Figure square = new Square(new Point(0.0f, 0.0f), 10.0f);
        Figure triangle = new Triangle(
                new Point(0.0f, 0.0f),
                new Point(10.0f, 0.0f),
                new Point(0.0f, 10.0f)
        );

        // Работа с утилитным классом
        System.out.println("Circle area: " + FigureUtil.area(circle));
        FigureUtil.draw(circle, Color.BLUE);

        System.out.println("\nRectangle perimeter: " + FigureUtil.perimeter(rect));
        FigureUtil.draw(rect);

        System.out.println("\nSquare area: " + FigureUtil.area(square));
        FigureUtil.draw(square, Color.RED);

        System.out.println("\nTriangle perimeter: " + FigureUtil.perimeter(triangle));
        FigureUtil.draw(triangle, Color.GREEN);
    }
}