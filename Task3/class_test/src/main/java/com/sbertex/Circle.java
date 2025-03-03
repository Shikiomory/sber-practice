package com.sbertex;

public class Circle extends Figure implements Drawable {
    private double radius;

    public Circle(Point center, double radius) {
        super(center);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.printf("Нарисован круг с центром (%.1f, %.1f) и радиусом %.1f\n",
                point.getX(), point.getY(), radius);
    }

    @Override
    public void draw(Color color) {
        System.out.printf("Нарисован %s круг с центром (%.1f, %.1f) и радиусом %.1f\n",
                color, point.getX(), point.getY(), radius);
    }
}
