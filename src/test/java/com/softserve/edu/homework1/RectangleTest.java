package com.softserve.edu.homework1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class RectangleTest {

    @Test
    void testDefaultConstructor() {
        Rectangle rectangle = new Rectangle();
        assertEquals(1.0, rectangle.getWidth());
        assertEquals(1.0, rectangle.getHeight());
        assertEquals(90.0, rectangle.getAngle());
    }

    @Test
    void testParameterizedConstructor() {
        Rectangle rectangle = new Rectangle(5.0, 10.0);
        assertEquals(5.0, rectangle.getWidth());
        assertEquals(10.0, rectangle.getHeight());
    }

    @Test
    void testSetWidth() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(7.0);
        assertEquals(7.0, rectangle.getWidth());
    }

    @Test
    void testSetWidthWithInvalidValue() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(-3.0));
    }

    @Test
    void testSetHeight() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(4.0);
        assertEquals(4.0, rectangle.getHeight());
    }

    @Test
    void testSetHeightWithInvalidValue() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-5.0));
    }

    @Test
    void testCalculateArea() {
        Rectangle rectangle = new Rectangle(4.0, 5.0);
        assertEquals(20.0, rectangle.calculateArea(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, 3.0, 6.0",
            "5.0, 10.0, 50.0",
            "1.5, 2.5, 3.75"
    })
    void testCalculateAreaParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedArea, rectangle.calculateArea(), 0.001);
    }

    @Test
    void testCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(3.0, 6.0);
        assertEquals(18.0, rectangle.calculatePerimeter(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, 3.0, 10.0",
            "5.0, 10.0, 30.0",
            "1.5, 2.5, 8.0"
    })
    void testCalculatePerimeterParameterized(double width, double height, double expectedPerimeter) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedPerimeter, rectangle.calculatePerimeter(), 0.001);
    }

    @Test
    void testGetDiagonal() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(5.0, rectangle.getDiagonal(), 0.001); // 3-4-5 triangle
    }

    @ParameterizedTest
    @CsvSource({
            "3.0, 4.0, 5.0",
            "5.0, 12.0, 13.0",
            "8.0, 15.0, 17.0"
    })
    void testGetDiagonalParameterized(double width, double height, double expectedDiagonal) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedDiagonal, rectangle.getDiagonal(), 0.001);
    }

    @Test
    void testAngleIsAlwaysNinety() {
        Rectangle rectangle = new Rectangle(3.0, 6.0);
        assertEquals(90.0, rectangle.getAngle());
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 4.0",
            "5.0, -3.0",
            "-1.0, -1.0"
    })
    void testInvalidWidthAndHeight(double width, double height) {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(width, height));
    }
}

