package com.softserve.homwork09.hometask2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;

public class CollectionOfNumbersTest {

    private CollectionOfNumbers collectionOfNumbers;
    private final int count = 20;
    private final int maxValue = 100;

    @BeforeEach
    void setUp() {
        collectionOfNumbers = new CollectionOfNumbers();
        collectionOfNumbers.generateRandomNumbers(count, maxValue);
    }

    @Test
    @DisplayName("Checking generateRandomNumbers() generated not empty collection")
    void checkGenerateRandomNumbersIsNotEmpty() {
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        assertFalse(testCollection.isEmpty(), "Collection is not empty");
    }

    @Test
    @DisplayName("Checking generateRandomNumbers() generated values from 1 to 100")
    void checkGenerateRandomNumbersValue() {
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        assertTrue(testCollection.stream().allMatch(num -> num >= 1 && num <= maxValue), "All elements of collection in range from 1 to 100");
    }

    @Test
    @DisplayName("Checking generateRandomNumbers() generated collection with actual size length")
    void checkGenerateRandomNumbersArrayLength() {
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        assertEquals(count, testCollection.size(), "The size of collection is correct ");
    }

    @Test
    @DisplayName("Negative test: checking generateRandomNumbers() generated collection with actual size length")
    void checkGenerateRandomNumbersArrayLengthNegative() {
        int incorrectMaxValue = 90;
        int incorrectCount = 30;
        collectionOfNumbers.generateRandomNumbers(incorrectCount, incorrectMaxValue);
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        assertNotEquals(count, testCollection.size(), "The size of the collection should match the expected value");

    }

    @Test
    @DisplayName("Checking generateRandomNumbers() not generated negative and zero values")
    void checkGenerateRandomNumbersNegativeValue() {
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        assertFalse(testCollection.stream().allMatch(num -> num <= 0), "Collection have not negative and zero values");
    }

    @Test
    @DisplayName("Checking generateRandomNumbers() not generated more than 100 values")
    void checkGenerateRandomNumbersValueMoreThanMaxValues() {
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        assertFalse(testCollection.stream().allMatch(num -> num > maxValue), "Collection have not negative and zero values");
    }

    @Test
    @DisplayName("Check Exception is throw when collection is empty")
    void checkIfEmptyThrowsExceptionIfItNull() {
        collectionOfNumbers.generateRandomNumbers(0, maxValue);
        assertThrows(NoSuchElementException.class, () -> {
            collectionOfNumbers.getNumbers();
        }, "Expected NoSuchElementException");
    }

    @Test
    @DisplayName("Check findMin() return min value of collection")
    void checkFindMinCorrectValues() {
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        int expectedMin = testCollection.stream().min(Integer::compareTo).orElseThrow();
        assertEquals(expectedMin, collectionOfNumbers.findMin(),
                "findMin() return min of all values");
    }

    @Test
    @DisplayName("Check findMin() throw Exception then collection is empty")
    void checkFindMinEmptyCollection() {
        collectionOfNumbers.generateRandomNumbers(0, maxValue);
        assertThrows(NoSuchElementException.class, () -> {
            collectionOfNumbers.findMin();
        }, "Expected NoSuchElementException");
    }

    @Test
    @DisplayName("Check findMin() with add array without generateRandomNumbers()")
    void checkFindMinManualCollection() {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 4, 6, 17, 1, 20));
        assertEquals(1, testClassOfCollectionOfNumbers.findMin(),
                "min value of manual collection found correct");
    }

    @ParameterizedTest
    @DisplayName("Check findMin() with many arrays inputted manually")
    @CsvSource({
            "2, 3, 1, 99, 55,    1",
            "40, 12, 3, 34, 11,    3",
            "87, 12, 45, 11, 67,    11",
            "12, 45, 6, 56, 77,    6",
            "2, 76, 4, 2, 5,    2",
    })
    void checkFindMinManualCollectionParametrized(
            int num1, int num2, int num3, int num4, int num5, int expectedMin) {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(num1, num2, num3, num4, num5));
        assertEquals(expectedMin, testClassOfCollectionOfNumbers.findMin(),
                "min value of parametrized test manual input is correct");
    }

    @Test
    @DisplayName("Check findMin() with zero numbers")
    void checkFindMinZeroNumber() {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 4, 0, 17, 1, 20));
        assertEquals(0, testClassOfCollectionOfNumbers.findMin(),
                "expected min number is 0 ");
    }

    @Test
    @DisplayName("Check findMin() with negative numbers")
    void checkFindMinNegativeNumber() {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(23, 5, 2, 54, 1, -20));
        assertEquals(-20, testClassOfCollectionOfNumbers.findMin(),
                "expected min number is -20 ");
    }

    @Test
    @DisplayName("Check findMax() return max value of collection")
    void checkFindMaxCorrectValues() {
        List<Integer> testCollection = collectionOfNumbers.getNumbers();
        int expectedMax = testCollection.stream().max(Integer::compareTo).orElseThrow();
        assertEquals(expectedMax, collectionOfNumbers.findMax(),
                "findMax() return max of all correct values");
    }

    @Test
    @DisplayName("Check findMax() throw Exception then collection is empty")
    void checkFindMaxEmptyCollection() {
        collectionOfNumbers.generateRandomNumbers(0, maxValue);
        assertThrows(NoSuchElementException.class, () -> {
            collectionOfNumbers.findMax();
        }, "Expected NoSuchElementException");
    }

    @Test
    @DisplayName("Check findMax() with add array without generateRandomNumbers()")
    void checkFindMaxManualCollection() {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 4, 6, 17, 1, 20));
        assertEquals(20, testClassOfCollectionOfNumbers.findMax(),
                "max value of manual collection found correct");
    }

    @ParameterizedTest
    @DisplayName("Check findMax() with many arrays inputted manually")
    @CsvSource({
            "2, 3, 1, 99, 55,    99",
            "40, 12, 3, 34, 11,    40",
            "87, 12, 45, 11, 67,    87",
            "12, 45, 6, 56, 77,    77",
            "2, 76, 4, 2, 5,    76",
    })
    void checkFindMaxManualCollectionParametrized(
            int num1, int num2, int num3, int num4, int num5, int expectedMax) {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(num1, num2, num3, num4, num5));
        assertEquals(expectedMax, testClassOfCollectionOfNumbers.findMax(),
                "max value of parametrized test manual input is correct");
    }

    @Test
    @DisplayName("Check findMax() with zero numbers")
    void checkFindMaxZeroNumber() {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 4, 0, 17, 0, 20));
        assertEquals(20, testClassOfCollectionOfNumbers.findMax(),
                "expected max value found correct in collection included zero elements ");
    }

    @Test
    @DisplayName("Check findMax() with negative numbers")
    void checkFindMaxNegativeNumber() {
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 4, 0, 17, 1, -20));
        assertEquals(17, testClassOfCollectionOfNumbers.findMax(),
                "expected max value found correct in collection included negative elements");
    }


    @ParameterizedTest
    @DisplayName("Check calculateAverage() with many arrays inputted manually")
    @CsvSource({
            "2, 3, 1, 99, 55,    32.0",
            "40, 12, 3, 34, 11,    20.0",
            "87, 12, 45, 11, 67,    44.4",
            "12, 45, 6, 56, 77,    39.2",
            "2, 76, 4, 2, 5,    17.8",
    })
    void checkCalculateAverageParametrized(
            int num1, int num2, int num3, int num4, int num5, double expectedAverage){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(num1, num2, num3, num4, num5));
        assertEquals(expectedAverage, testClassOfCollectionOfNumbers.calculateAverage(),
                "calculateAverage() return average for each of arrays inputted manually");
    }

    @ParameterizedTest
    @DisplayName("Check calculateAverage() with arrays included negative and zero values")
    @CsvSource({
            "0, 3, 1, 99, 0,    20.6",
            "40, -12, 3, 34, -11,    10.8",
            "0, 0, 0, 0, 0,    0.0",
            "-12, 0, 6, 0, -77,    -16.6",
            "-2, -76, -4, -2, -5,    -17.8",
    })
    void checkCalculateAverageParametrizedNegative(
            int num1, int num2, int num3, int num4, int num5, double expectedAverage){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(num1, num2, num3, num4, num5));
        assertEquals(expectedAverage, testClassOfCollectionOfNumbers.calculateAverage(),
                "calculateAverage() return average for each of arrays included negative and zero values");
    }

    @Test
    @DisplayName("Check removeAllEven() for correct values")
    void checkRemoveAllEven(){
        collectionOfNumbers.generateRandomNumbers(20, 100);
        List<Integer> testCollection = collectionOfNumbers.getNumbers();

        List<Integer> expectedCollection = testCollection.stream().filter(number -> number % 2 != 0).toList();

        assertEquals(expectedCollection, collectionOfNumbers.removeAllEven(),
                "removeAllEven() should return only odd numbers");
    }

    @ParameterizedTest
    @DisplayName("Check removeAllEven() with many arrays inputted manually")
    @CsvSource({
            "'10 40 20 8 6 2', ''",
            "'11 41 21 9 7 3', '11 41 21 9 7 3'",
            "'10 41 20 9 6 3', '41 9 3'",
            "'0 0 20 9 0 3', '9 3'"
    })
    void checkRemoveAllEvenParametrized(String input, String expected){
        List<Integer> inputList = input.isEmpty()
                ?List.of()
                :Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> expectedList = expected.isEmpty()
                ?List.of()
                :Arrays.stream(expected.split(" ")).map(Integer::parseInt).collect((Collectors.toList()));

        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(inputList);

        assertEquals(expectedList, testClassOfCollectionOfNumbers.removeAllEven(),
                "removeAllEven() removed all even successfully");
    }

    @Test
    @DisplayName("Check removeAllEven() throws NoSuchElementException")
    void checkRemoveAllEvenNullException(){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        assertThrows(NoSuchElementException.class, () -> {
            testClassOfCollectionOfNumbers.removeAllEven();
        }, "Expected NoSuchElementException");
    }

    @Test
    @DisplayName("Checked checkGivenNumber() for correct values")
    void checkGivenNumberPositive(){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 34, 4, 50, 15, 7, 89));
        int givenNumber = 15;
        assertTrue(testClassOfCollectionOfNumbers.checkGivenNumber(givenNumber),
                "expected true then Collection included givenNumber");
    }

    @Test
    @DisplayName("Checked checkGivenNumber() Negative test")
    void checkGivenNumberNegative(){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 34, 4, 50, 15, 7, 89));
        int givenNumber = 33;
        assertFalse(testClassOfCollectionOfNumbers.checkGivenNumber(givenNumber),
                "expected false then Collection not included givenNumber");
    }

    @Test
    @DisplayName("Checked checkGivenNumber() throws NoSuchElementException")
    void checkGivenNumberNullCollection(){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of());
        int givenNumber = 15;
        assertThrows(NoSuchElementException.class, () ->{
            testClassOfCollectionOfNumbers.checkGivenNumber(givenNumber);
        },"expected true then Collection included givenNumber");
    }

    @Test
    @DisplayName("Checked checkGivenNumber() then Collection consist of only zero elements")
    void checkGivenNumberZeroCollection(){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(0, 0, 0, 0));
        int givenNumber = 15;
        assertFalse(testClassOfCollectionOfNumbers.checkGivenNumber(givenNumber),
                "expected false then Collection consist of only zero elements");
    }

    @Test
    @DisplayName("Check sortByAscending() for Collection with correct values")
    void checkSortByAscendingPositive(){
        collectionOfNumbers.generateRandomNumbers(5, 50);
        List<Integer> expectedCollection = collectionOfNumbers.getNumbers();
        List<Integer> sortedExpected = expectedCollection.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        assertEquals(sortedExpected, collectionOfNumbers.sortByAscending(),
                "expected result is sorting of collection");
    }

    @Test
    @DisplayName("Check sortByAscending() for Collection with incorrect negative values")
    void checkSortByAscendingIncludeNegativeNumbers(){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of(2, 4, 10, 11, 23, 55, -2, -33));
        List<Integer> expectedCollection = testClassOfCollectionOfNumbers.getNumbers();
        List<Integer> sortedExpectedCollection = expectedCollection.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        assertEquals(sortedExpectedCollection, testClassOfCollectionOfNumbers.sortByAscending(),
                "Expected that sortedByAscending() working correctly with negative values to");
    }

    @Test
    @DisplayName("Check sortByAscending() throws NoSuchElementException then collection is empty")
    void checkSortByAscendingEmptyCollection(){
        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(List.of());
        assertThrows(NoSuchElementException.class, () -> {
            testClassOfCollectionOfNumbers.sortByAscending();
        }, "message");
    }

    @ParameterizedTest
    @DisplayName("Check ortByAscending() with many arrays inputted manually")
    @CsvSource({
            "'10 40 20 8 6 2', '2 6 8 10 20 40'",
            "'11 41 21 9 7 3', '3 7 9 11 21 41'",
            "'10 41 20 0 -3 -33', '-33 -3 0 10 20 41'",
            "'0 0 20 9 0 3', '0 0 0 3 9 20'"
    })
    void checkSortByAscendingParametrized(String input, String expected){
        List<Integer> inputList = input.isEmpty()
                ?List.of()
                :Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> expectedList = expected.isEmpty()
                ?List.of()
                :Arrays.stream(expected.split(" ")).map(Integer::parseInt).collect((Collectors.toList()));

        TestClassOfCollectionOfNumbers testClassOfCollectionOfNumbers = new TestClassOfCollectionOfNumbers();
        testClassOfCollectionOfNumbers.setNumbers(inputList);

        assertEquals(expectedList, testClassOfCollectionOfNumbers.sortByAscending(),
                "Expected that sortedByAscending() working correctly with many arrays to");
    }



}
    

