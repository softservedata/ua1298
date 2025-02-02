package HW9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {
    private static Rectangle rectangle;

    @BeforeAll
    public static void setup() {
        rectangle = new Rectangle();
    }

    @ParameterizedTest(name = "{index} => width={0}, height={1}")
    @DisplayName("Test should initialize width and height to provided values")
    @CsvSource({
            "2, 4",
            "5, 10",
            "3.5, 7.2",
            "6, 6"
    })
    public void checkValidRectangle(double width, double height) {
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        Assertions.assertTrue(rectangle.isValidRectangle());
    }

    @ParameterizedTest(name = "{index} => width={0}, height={1}")
    @DisplayName("Invalid rectangle dimensions throw IllegalArgumentException")
    @CsvSource({
            "-1, 4",
            "3, -5",
            "0, 7",
            "-3, -6"
    })

    public void checkInvalidRectangle(double width, double height) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(width, height);
        });
        System.out.println("\t\tMessage = " + thrown.getMessage());
        Assertions.assertTrue(thrown.getMessage().contains(Rectangle.RECTANGLE_NOT_VALID));
    }

    @ParameterizedTest(name = "{index} => width={0}, height={1}, expectedPerimeter={2}")
    @DisplayName("checkPerimeter() should return correct area for different width and height values.")
    @CsvSource({
            "2, 4, 12",
            "5, 10, 30",
            "3.5, 7.2, 21.4"
    })
    public void checkPerimeter(double width, double height, double expectedPerimeter) {
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        Assertions.assertEquals(expectedPerimeter, rectangle.getPerimeter(), 0.0001);
    }

    @ParameterizedTest(name = "{index} => width={0}, height={1}, expectedArea={2}")
    @DisplayName("checkArea() should return correct area for different width and height values.")
    @CsvSource({
            "2, 4, 8",
            "5, 10, 50",
            "3.5, 7.2, 25.2"
    })
    public void checkArea(double width, double height, double expectedArea) {
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        Assertions.assertEquals(expectedArea, rectangle.getArea(), 0.0001);
    }

    @ParameterizedTest(name = "{index} => width={0}, height={1}, expectedDiagonal={2}")
    @DisplayName("checkDiagonal() should return correct diagonal of rectangle.")
    @CsvSource({
            "3, 4, 5",
            "5, 12, 13",
            "8, 15, 17"
    })
    public void checkDiagonal(double width, double height, double expectedDiagonal) {
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        Assertions.assertEquals(expectedDiagonal, rectangle.getDiagonal(), 0.0001);
    }
}