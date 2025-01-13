package com.softserve.edu.homework9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {
    Rectangle rectangle = new Rectangle();
    @Test
   public void testConstructor() {
        assertEquals(1.0,rectangle.getHeight());
        assertEquals(1.0,rectangle.getWidth());
        assertEquals(90.0,rectangle.getAngle());
    }

    @Test
    void testParameterizedConstructor() {
        Rectangle rectangle = new Rectangle(10.0,5.0);
        assertEquals(10.0,rectangle.getWidth());
        assertEquals(5.0,rectangle.getHeight());
    }

    @Test
    void testCalculateArea() {
        Rectangle rectangle = new Rectangle(10.0,5.0);
        double area = rectangle.calculateArea();
        assertEquals(50.0,area,"The area was calculated incorrectly.");
    }

    @Test
    void testCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(10.0,5.0);
        double perimeter = rectangle.calculatePerimeter();
        assertEquals(30.0,perimeter,"The perimeter was calculated incorrectly.");
    }

    @Test
    void testGetDiagonal() {
        Rectangle rectangle = new Rectangle(3.0,4.0);
        double diagonal = rectangle.getDiagonal();
        assertEquals(5.0,diagonal,"The diagonal was calculated incorrectly.");
    }

    @Test
    void testSetWithNegativeValues() {
        assertThrows(IllegalArgumentException.class,() ->
                rectangle.setHeight(-4));
        assertThrows(IllegalArgumentException.class,() ->
                rectangle.setWidth(0));
    }
}
