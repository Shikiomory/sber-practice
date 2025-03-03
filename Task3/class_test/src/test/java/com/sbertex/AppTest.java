package com.sbertex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void CircleArea() {
        Figure circle = new Circle(new Point(0.0f, 0.0f), 10.0f);
        double answer = circle.area();
        double expected = 10.0f * 10.0f * Math.PI;
        assertEquals(expected, answer);
    }

    @Test
    public void RectangleArea() {
        Figure rect = new Rectangle(new Point(0.0f, 0.0f), new Point(10.0f, 20.0f));
        double answer = rect.area();
        double expected = 10.0f * 20.0f;
        assertEquals(expected, answer);
    }

    @Test
    public void SquareArea() {
        Figure square = new Square(new Point(0.0f, 0.0f), 10.0f);
        double answer = square.area();
        double expected = 10.0f * 10.0f;
        assertEquals(expected, answer);
    }

    @Test
    public void TriangleArea() {
        Figure triangle = new Triangle(
                new Point(0.0f, 0.0f),
                new Point(10.0f, 0.0f),
                new Point(0.0f, 10.0f)
        );
        double answer = triangle.area();
        double expected = 10.0f * 10.0f * 0.5;
        assertEquals(expected, answer);
    }
}
