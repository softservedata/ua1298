package com.softserve.hw09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

    // Positive scenario
    // 1. Testing default constructor
    @Test
    void testDefaultConstructor() {
        Rectangle rectangle = new Rectangle();
        assertEquals(0, rectangle.getWidth(), "Default should be 0.");
        assertEquals(0, rectangle.getHeight(), "Default should be 0.");
        assertEquals(90, rectangle.getAngle(), "Default angle should be 90.");
    }

    // 2. Testing constructor with params.
    @Test
    void testParametrizedConstructor() {
        Rectangle rectangle = new Rectangle(8, 16);
        assertEquals(8, rectangle.getWidth(), "Width should be 8.");
        assertEquals(16, rectangle.getHeight(), "Height should be 16.");
        assertEquals(90, rectangle.getAngle(), "Angle should be 90.");
    }

    // 3. Testing calculateArea() method.
    @Test
    void testCalculateArea() {
        Rectangle rectangle = new Rectangle(5, 10);
        assertEquals(50, rectangle.calculateArea(), "Area should be 50.");
    }

    // 4. Testing calculatePerimeter() method.
    @Test
    void testCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(3, 9);
        assertEquals(24, rectangle.calculatePerimeter(), "Perimeter should be 24.");
    }

    // 5. Testing getDiagonal() method.
    @Test
    void testGetDiagonal() {
        Rectangle rectangle = new Rectangle(3, 4);
        assertEquals(5, rectangle.getDiagonal(), "Diagonal should be 11.18.");
    }

    // Negative scenario
    // 1. Setting other angle.
    @Test
    void testInvalidAngle() {
        Rectangle rectangle = new Rectangle();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rectangle.setAngle(45);
        });
        assertEquals("Angle must be 90 degrees only.", exception.getMessage());
    }

    // 2. Testing width with negative numbers.
    @Test
    void testNegativeWidth() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(-5);
        assertEquals(-5, rectangle.getWidth(), "Width can be negative but should be checked.");
    }

    // 3. Testing height with negative numbers.
    @Test
    void testNegativeHeight() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(-15);
        assertEquals(-15, rectangle.getHeight(), "Height can be negative but should be checked.");
    }
}