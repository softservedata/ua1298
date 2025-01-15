package com.softserve.homwork09.hometask1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private  final Rectangle rectangle = new Rectangle();
    private final double delta = 0.0001; //add acceptable calculation error margin

    @Test
    @DisplayName("Default Constructor create Rectangle with height = 1 and width = 2, angle = 90 ")
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
    @DisplayName("calculatedArea() should return correct area for valid width and height")
    public void testCalculateArea() {
        Rectangle rectangle = new Rectangle(4.0, 3.0);
        assertEquals(12.0, rectangle.calculateArea(), delta, "Numbers are not equal within the delta");
    }

    @ParameterizedTest
    @DisplayName("calculatedArea() should return correct area for different width and height values")
    @CsvSource({
            "2.0, 10.0, 20.0",
            "1.0, 1.0, 1.0",
            "1.5, 4.7, 7.05",
            "0.2, 0.4, 0.08"
    })
    public void testCalculateAreaParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedArea, rectangle.calculateArea(), delta, "Numbers are not equal within the delta");
    }

    @Test
    @DisplayName("calculatedPerimeter() should return correct perimeter for valid width and height")
    public void testCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(7.0, 6.0);
        assertEquals(26.0, rectangle.calculatePerimeter());
    }

    @ParameterizedTest
    @DisplayName("calculatedPerimeter() should return correct area for different width and height values")
    @CsvSource({
            "2.0, 10.0, 24.0",
            "1.0, 1.0, 4.0",
            "1.5, 4.7, 12.4",
            "1.2, 1.4, 5.2"
    })
    public void testCalculatePerimeterParameterized(double width, double height, double expectedArea) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedArea, rectangle.calculatePerimeter(), delta, "Numbers are not equal within the delta");
    }

    @Test
    @DisplayName("getDiagonal() should return correct diagonal for valid width and height")
    public void testgetDiagonal() {
        Rectangle rectangle = new Rectangle(7.0, 6.0);
        assertEquals(9.219544, rectangle.getDiagonal(), delta, "Numbers are not equal within the delta");
    }

    @ParameterizedTest
    @DisplayName("getDiagonal() should return correct diagonal for different width and height values")
    @CsvSource({
            "3.0, 4.0, 5.0",
            "1.0, 1.0, 1.414213",
            "1.5, 4.7, 4.93355",
            "1.2, 1.4, 1.8439"
    })
    public void testGetDiagonalParameterized(double width, double height, double expectedDiagonal) {
        Rectangle rectangle = new Rectangle(width, height);
        assertEquals(expectedDiagonal, rectangle.getDiagonal(), delta, "Numbers are not equal within the delta");
    }


    @Test
    @DisplayName("setHeight() should throw IllegalArgumentException when height is negative")
    public void testSetHeightWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-5),
                "Expected setHeight() to throw, but it didn't");
    }

    @Test
    @DisplayName("setWidth() should throw IllegalArgumentException when width is negative")
    public void testSetWidthWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(-3),
                "Expected setWidth() to throw, but it didn't");
    }

    @Test
    @DisplayName("isItRectangle() should throw exception when one side is zero")
    public void testIsItRectangleOneZeroSide(){
        Rectangle rectangle = new Rectangle();
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(0.0),
                "Expected isItRectangle() to throw, but it didn't");
    }


    @Test
    @DisplayName("Constructor should throw IllegalArgumentException when height is negative")
    public void testConstructorWithNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(4.0, -3.0),
                "Expected constructor to throw, but it didn't");
    }


    @Test
    @DisplayName("isItRectangle() should throw IllegalArgumentException when height and width are zero values")
    public void testIsItRectangle(){
        TestRectangle testRectangle = new TestRectangle(0.0,12.0);
        assertThrows(IllegalArgumentException.class, () -> testRectangle.isItRectangle(),
                "Width and height must be positive, not zero");
    }

    @Test
    @DisplayName("calculatedArea() should throw IllegalArgumentException when height and width are zero values")
    public void testCalculatedAreaNegativeAllSides() {
        TestRectangle testRectangle = new TestRectangle(0.0,0.0);
        assertThrows(IllegalArgumentException.class, () -> testRectangle.calculateArea(),
                "Width and height must be positive, not zero");

    }

    @ParameterizedTest
    @DisplayName("calculatedArea() should throw IllegalArgumentException when height or width is zero")
    @CsvSource({
            "0, 2.0",
            "2.0, 0",
            "0, -2.0",
            "-2.0, 0"
    })
    public void testCalculatedAreaZeroParametrized(double width, double height) {
        TestRectangle testRectangle = new TestRectangle(width, height);
        assertThrows(IllegalArgumentException.class, () -> testRectangle.calculateArea(),
                "Width and height must be positive");

    }

    @ParameterizedTest
    @DisplayName("calculatedArea() should throw IllegalArgumentException when height or width is negative")
    @CsvSource({
            "-3.0, 2.0",
            "2.0, -3.0",
            "-2.0, -3.0"
    })
    public void testCalculatedAreaNegativeParametrized(double width, double height) {
        TestRectangle testRectangle = new TestRectangle(width, height);
        assertThrows(IllegalArgumentException.class, () -> testRectangle.calculateArea(),
                "Width and height must be positive");

    }

    @ParameterizedTest
    @DisplayName("calculatedPerimeter() should throw IllegalArgumentException when height or width is negative or equals zero")
    @CsvSource({
            "-3.0, 2.0",
            "2.0, -3.0",
            "-2.0, -3.0",
            "2.0, 0",
            "0, 3.0",
            "0, 0"
    })
    public void testCalculatedPerimeterNegativeOrZeroValuesParametrized(double width, double height) {
        TestRectangle testRectangle = new TestRectangle(width, height);
        assertThrows(IllegalArgumentException.class, () -> testRectangle.calculateArea(),
                "Width and height must be positive");

    }

    @ParameterizedTest
    @DisplayName("getDiagonal() throw IllegalArgumentException when height or width is negative or equal zero")
    @CsvSource({
            "-3.0, -4.0",
            "-1.0, 1.0",
            "0, 1.0",
            "0, -1.0"
    })
    public void testGetDiagonalhNegativeOrZeroValuesParameterized(double width, double height) {
        TestRectangle testRectangle = new TestRectangle(width, height);
        assertThrows(IllegalArgumentException.class, () -> testRectangle.getDiagonal(),
                "Width and height must be positive");
    }

}




