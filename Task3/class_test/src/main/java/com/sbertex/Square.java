package com.sbertex;

public class Square extends Rectangle {
    public Square(Point point, double side) {
        super(point, new Point(point.getX() + side, point.getY() + side));
    }

    @Override
    public void draw() {
        System.out.printf("Нарисован квадрат по координатам (%.1f, %.1f) и (%.1f, %.1f)\n",
                point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    @Override
    public void draw(Color color) {
        System.out.printf("Нарисован %s квадрат по координатам (%.1f, %.1f) и (%.1f, %.1f)\n",
                color, point.getX(), point.getY(), point2.getX(), point2.getY());
    }
}