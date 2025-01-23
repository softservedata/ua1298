package com.softserve.edu02ju;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

enum Size {
    XXS, XS, S, M, L, XL, XXL, XXXL;
}

@DisplayName("Should pass the method parameters provided by the sumProvider() method")
public class ParJUnit5Test {
    // Field data provider
    private static List<String> cities = Arrays.asList("Madrid", "Rome", "Paris", "London");

    @ParameterizedTest
    @FieldSource("cities")
    void isBlank_ShouldReturnFalseWhenTheArgHasAtLEastOneCharacter(String arg) {
        System.out.println("arg = " + arg);
        Assertions.assertFalse(isBlank(arg));
    }


    private boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        return str.isBlank();
    }

    @DisplayName("Should pass a non-null message to our test method")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    @NullAndEmptySource
    void shouldPassNonNullMessageAsMethodParameter(String message) {
        System.out.println("Message = " + message);
        Assertions.assertNotNull(message);
    }

    @ParameterizedTest
    @EnumSource(value = Size.class, names = {"L", "XL", "XXL", "XXXL"})
    void testEnumInclude(Size size) {
        System.out.println("size = " + size);
        Assertions.assertTrue(EnumSet.allOf(Size.class).contains(size));
    }

    @ParameterizedTest
    @EnumSource(value = Size.class, mode = EXCLUDE, names = {"XXS", "XS", "S"})
    void testEnumExclude(Size size) {
        System.out.println("size = " + size);
        EnumSet<Size> excludeSmallSize = EnumSet.range(Size.M, Size.XXXL);
        Assertions.assertTrue(excludeSmallSize.contains(size));
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5"
    })
    void sum(int a, int b, int sum) {
        Assertions.assertEquals(sum, a + b);
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvSource(value={"1 : 1 : 2", "2 : 3 : 5"}, delimiter = ':')
    void sum4(int a, int b, int sum) {
        System.out.println("a = " + a + "  b = " + b + "  sum = " + sum);
        Assertions.assertEquals(sum, a + b);
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void sum2(int a, int b, int sum) {
        System.out.println("a = " + a + "  b = " + b + "  sum = " + sum);
        Assertions.assertEquals(sum, a + b);
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvFileSource(resources = "/test-data22.csv", delimiter = ';', numLinesToSkip = 1)
    void sum22(int a, int b, int sum) {
        System.out.println("a = " + a + "  b = " + b + "  sum = " + sum);
        Assertions.assertEquals(sum, a + b);
    }

    private static Stream<String> shouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  \t ", "a");
    }

    @ParameterizedTest
    @MethodSource//("shouldReturnTrueForNullOrBlankStringsOneArgument")
    public void shouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        Assertions.assertTrue(isBlank(input));
    }

    @ParameterizedTest
    @MethodSource("com.softserve.edu02ju.StringParams#blankStrings")
    void shouldReturnTrue(String input) {
        System.out.println("input = " + input);
        Assertions.assertTrue(isBlank(input));
    }

    private static Stream<Arguments> sumProvider() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 3, 5)
        );
    }

    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("sumProvider")
    void sum3(int a, int b, int sum) {
        System.out.println("a = " + a + "  b = " + b + "  sum = " + sum);
        Assertions.assertEquals(sum, a + b);
    }

    public static Object[][] numbers() {
        return new Object[][]{
                {new int[]{1, 2, 3, 4, 5}, 3},
                {new int[]{5, 4, 3, 2, 1}, 4},
                {new int[]{1, 2, 3, 4, 10}, 10}
        };
    }

    @ParameterizedTest
    @MethodSource("numbers")
    public void testThree(int[] arr, int num) {
        System.out.println("\t\t@Test testThree(), num = " + num);
        boolean isExist = false;
        for (int i = 0; i<arr.length; i++) {
            if (arr[i] == num) {
                isExist = true;
                break;
            }
        }
        Assertions.assertTrue(isExist, "Array should contain the number");
    }
}
