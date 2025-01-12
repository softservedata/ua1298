package com.softserve.edu.hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class pr9Test {

    private pr9 analysis;

    @BeforeEach
    void setUp() {
        analysis = new pr9();
    }

    // Print all elements of the collection to the console
    @Test
    void testGenerateRandomNumbers() {
        List<Integer> numbers = analysis.generateRandomNumbers(20, 100);
        assertEquals(20, numbers.size());
        assertTrue(numbers.stream().allMatch(number -> number >= 1 && number <= 100));
    }

    // Find the minimum numbers in the collection
    @Test
    void testFindMin_Positive() {
        List<Integer> numbers = Arrays.asList(10, 20, 5, 15);
        assertEquals(5, analysis.findMin(numbers));
    }

    //  Empty list error test for minimum value
    @Test
    void testFindMin_Negative_EmptyList() {
        List<Integer> numbers = Arrays.asList();
        assertThrows(NoSuchElementException.class, () -> analysis.findMin(numbers));
    }

    // Find the maximum  numbers in the collection
    @Test
    void testFindMax_Positive() {
        List<Integer> numbers = Arrays.asList(10, 20, 5, 15);
        assertEquals(20, analysis.findMax(numbers));
    }

    // Calculate the average value
    @Test
    void testCalculateAverage_Positive() {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40);
        assertEquals(25.0, analysis.calculateAverage(numbers));
    }

    // Remove all even numbers
    @Test
    void testRemoveEvenNumbers() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expected = Arrays.asList(1, 3, 5);
        assertEquals(expected, analysis.removeEvenNumbers(numbers));
    }

    // Check if the collection contains a given number
    @Test
    void testContainsNumber_Positive() {
        List<Integer> numbers = Arrays.asList(10, 20, 30);
        assertTrue(analysis.containsNumber(numbers, 20));
    }

    @Test
    void testContainsNumber_Negative() {
        List<Integer> numbers = Arrays.asList(10, 20, 30);
        assertFalse(analysis.containsNumber(numbers, 50));
    }

    // Sort the collection
    @Test
    void testSortNumbers() {
        List<Integer> numbers = Arrays.asList(30, 10, 20);
        List<Integer> expected = Arrays.asList(10, 20, 30);
        assertEquals(expected, analysis.sortNumbers(numbers));
    }
}
