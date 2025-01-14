package com.softserve.homwork09.hometask1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

    private  final Rectangle rectangle = new Rectangle();

    @Test
    @DisplayName("Default Constructor create Rectangle with heigh = 1 and width = 2, angle = 90 ")
    public void testDefaultConstructor() {
        assertEquals(2.0, rectangle.getWidth());
        assertEquals(1.0, rectangle.getHeight());
        assertEquals(90.0, rectangle.getAngle());
    }

    @Test
    @DisplayName("Parameterized Constructor should initialize width and height")
    public void testParameterizedConstructor() {
        Rectangle rectangle1 = new Rectangle(7.0, 3.0);
        assertEquals(3.0, rectangle1.getHeight());
        assertEquals(7.0, rectangle1.getWidth());
    }

    @Test
    @DisplayName("getArea() should return correct area for valid width and height")
    public void testGetArea() {
        Rectangle rectangle = new Rectangle(4.0, 3.0);
        //add an error for comparison
        double delta = 0.00001;
        assertEquals(12.0, rectangle.getArea(), delta, "Numbers are not equal within the delta");
    }

    @ParameterizedTest
    @DisplayName("getArea() should return correct area for different width and height values")
    @CsvSource({
            "2.0, 10.0, 20.0",
            "1.0, 1.0, 1.0",
            "1.5, 4.7, 7.05",
            "0.2, 0.4, 0.08"
    })
    public void testGetAreaParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        //add an error for comparison
        double delta = 0.00001;
        assertEquals(expectedArea, rectangle.getArea(), delta, "Numbers are not equal within the delta");
    }

    @Test
    @DisplayName("getPerimeter() should return correct perimeter for valid width and height")
    public void testGetPerimeter() {
        Rectangle rectangle = new Rectangle(7.0, 6.0);
        assertEquals(26.0, rectangle.getPerimeter());
    }

    @ParameterizedTest
    @DisplayName("getPerimeter() should return correct area for different width and height values")
    @CsvSource({
            "2.0, 10.0, 24.0",
            "1.0, 1.0, 4.0",
            "1.5, 4.7, 12.4",
            "1.2, 1.4, 5.2"
    })
    public void testGetPerimeterParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        //add an error for comparison
        double delta = 0.00001;
        assertEquals(expectedArea, rectangle.getPerimeter(), delta, "Numbers are not equal within the delta");
    }



    @Test
    @DisplayName("setHeight() should throw IllegalArgumentException when height is negative")
    public void testSetHeightWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-5),
                "Expected setHeight() to throw, but it didn't");
    }

}




