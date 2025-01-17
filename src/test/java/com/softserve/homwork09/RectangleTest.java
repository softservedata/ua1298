package com.softserve.homwork09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    @Test
    @DisplayName("Default Constructor should initialize width and height to 1.0, angle to 90")
    public void testDefaultConstructor() {
        Rectangle rectangle = new Rectangle();
        assertEquals(1.0, rectangle.getWidth(), "Width should be 1.0");
        assertEquals(1.0, rectangle.getHeight(), "Height should be 1.0");
        assertEquals(90.0, rectangle.getAngle(), "Angle should be 90.0");
    }

    @Test
    @DisplayName("Parameterized Constructor should initialize width and height to provided values")
    public void testParameterizedConstructor() {
        Rectangle rectangle = new Rectangle(5.0, 10.0);
        assertEquals(5.0, rectangle.getWidth(), "Width should be 5.0");
        assertEquals(10.0, rectangle.getHeight(), "Height should be 10.0");
    }

    @Test
    @DisplayName("calculateArea() should return correct area for valid width and height")
    public void testCalculateArea() {
        Rectangle rectangle = new Rectangle(5.0, 10.0);
        assertEquals(50.0, rectangle.calculateArea(), "Area should be 50.0");
    }

    @ParameterizedTest
    @DisplayName("calculateArea() should return correct area for different width and height values")
    @CsvSource({
            "5.0, 10.0, 50.0",
            "7.0, 3.0, 21.0",
            "1.0, 10.0, 10.0",
            "6.0, 6.0, 36.0"
    })
    public void testCalculateAreaParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedArea, rectangle.calculateArea(), "Expected area does not match");
    }

    @Test
    @DisplayName("calculatePerimeter() should return correct perimeter for valid width and height")
    public void testCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(5.0, 10.0);
        assertEquals(30.0, rectangle.calculatePerimeter(), "Perimeter should be 30.0");
    }

    @Test
    @DisplayName("getDiagonal() should return correct diagonal length for valid width and height")
    public void testGetDiagonal() {
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        assertEquals(5.0, rectangle.getDiagonal(), "Diagonal should be 5.0");
    }

    @Test
    @DisplayName("setHeight() should throw IllegalArgumentException when height is negative")
    public void testSetHeightWithNegativeValue() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-5),
                "Expected setHeight() to throw, but it didn't");
    }

    @Test
    @DisplayName("setWidth() should throw IllegalArgumentException when width is negative")
    public void testSetWidthWithNegativeValue() {
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(-5),
                "Expected setWidth() to throw, but it didn't");
    }
}