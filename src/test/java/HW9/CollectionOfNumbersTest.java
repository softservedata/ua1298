package HW9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CollectionOfNumbersTest {
    private ArrayList<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = CollectionOfNumbers.generateRandomNumbers(20);
    }

    @Test
    void testGenerateRandomNumbers_size() {
        assertEquals(20, numbers.size(), "The list should contain 20 numbers.");
    }

    @Test
    void testFindMin_validNumbers() {
        int min = CollectionOfNumbers.findMin(numbers);
        assertTrue(min >= 1 && min <= 100, "Min value should be between 1 and 100.");
    }

    @Test
    void testFindMax_validNumbers() {
        int max = CollectionOfNumbers.findMax(numbers);
        assertTrue(max >= 1 && max <= 100, "Max value should be between 1 and 100.");
    }

    @Test
    void testCalculateAverage_validNumbers() {
        double average = CollectionOfNumbers.calculateAverage(numbers);
        assertTrue(average >= 1.0 && average <= 100.0, "Average value should be between 1 and 100.");
    }

    @Test
    void testRemoveEvenNumbers() {
        CollectionOfNumbers.removeEvenNumbers(numbers);
        for (int num : numbers) {
            assertTrue(num % 2 != 0, "The collection should contain only odd numbers after removing even ones.");
        }
    }

    @Test
    void testContainsNumber_true() {
        numbers.add(50); // Adding number 50
        assertTrue(CollectionOfNumbers.containsNumber(numbers, 50), "The collection should contain the number 50.");
    }

    @Test
    void testContainsNumber_false() {
        assertFalse(CollectionOfNumbers.containsNumber(numbers, 100), "The collection should not contain the number 100.");
    }

    @Test
    void testSortNumbers() {
        ArrayList<Integer> unsortedNumbers = new ArrayList<>(numbers);
        CollectionOfNumbers.sortNumbers(unsortedNumbers);
        for (int i = 1; i < unsortedNumbers.size(); i++) {
            assertTrue(unsortedNumbers.get(i - 1) <= unsortedNumbers.get(i), "The list should be sorted in ascending order.");
        }
    }

    @Test
    void testFindMin_emptyList() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        assertThrows(java.util.NoSuchElementException.class, () -> CollectionOfNumbers.findMin(emptyList), "Finding min in an empty list should throw exception.");
    }

    @Test
    void testFindMax_emptyList() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        assertThrows(java.util.NoSuchElementException.class, () -> CollectionOfNumbers.findMax(emptyList), "Finding max in an empty list should throw exception.");
    }
}