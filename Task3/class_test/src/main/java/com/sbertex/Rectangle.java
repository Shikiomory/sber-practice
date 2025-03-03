package com.sbertex;

public class Rectangle extends Figure implements Drawable {
    protected Point point2;

    public Rectangle(Point point1, Point point2) {
        super(point1);
        this.point2 = point2;
    }

    @Override
    public double area() {
        return Math.abs((point.getX() - point2.getX()) *
                (point.getY() - point2.getY()));
    }

    @Override
    public double perimeter() {
        return 2 * (Math.abs(point.getX() - point2.getX()) +
                Math.abs(point.getY() - point2.getY()));
    }

    @Override
    public void draw() {
        System.out.printf("Нарисован прямоугольник по координатам (%.1f, %.1f) и (%.1f, %.1f)\n",
                point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    @Override
    public void draw(Color color) {
        System.out.printf("Нарисован %s прямоугольник по координатам (%.1f, %.1f) и (%.1f, %.1f)\n",
                color, point.getX(), point.getY(), point2.getX(), point2.getY());
    }
}