package com.softserve.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {

    private static Triangle triangle;

    @BeforeAll
    public static void setup() {
        triangle = new Triangle();
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}")
    @CsvSource({
            "2, 4, 3",
            "2, 3, 4",
            "4, 2, 3",
            "4, 3, 2",
            "3, 2, 4",
            "3, 4, 2"
    })
    public void checkValidTriangle(double a, double b, double c) {
        Assertions.assertTrue(triangle.isValidTriangle(a, b, c));
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}")
    @CsvSource({
            "1, 4, 3",
            "1, 3, 4",
            "4, 1, 3",
            "4, 3, 1",
            "3, 1, 4",
            "3, 4, 1"
    })
    public void checkInvalidTriangle(double a, double b, double c) {
        Assertions.assertFalse(triangle.isValidTriangle(a, b, c));
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}, expected={3}")
    @CsvSource({
            "3, 4, 5, 6",
            "5, 4, 3, 6"
    })
    public void checkAreaValidTriangle(double a, double b, double c, double expected) {
        Assertions.assertEquals(expected, triangle.getArea(a, b, c));
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}")
    @CsvSource({
            "1, 2, 3",
            "1, 2, 5",
            "1, 3, -1"
    })
    public void checkAreaInvalidTriangle(double a, double b, double c) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            triangle.getArea(a, b, c);
        });
        System.out.println("\t\tMessage = " + thrown.getMessage());
        Assertions.assertTrue(thrown.getMessage().contains(Triangle.TRIANGLE_NOT_EXIST));
    }
}
