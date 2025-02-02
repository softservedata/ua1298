package HW9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class CollectionOfNumbers {public static ArrayList<Integer> generateRandomNumbers(int size) {
    ArrayList<Integer> numbers = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < size; i++) {
        numbers.add(random.nextInt(100) + 1);
    }
    return numbers;
}

    public static int findMin(ArrayList<Integer> numbers) {
        return Collections.min(numbers);
    }

    public static int findMax(ArrayList<Integer> numbers) {
        return Collections.max(numbers);
    }

    public static double calculateAverage(ArrayList<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
    }

    public static boolean containsNumber(ArrayList<Integer> numbers, int number) {
        return numbers.contains(number);
    }

    public static void sortNumbers(ArrayList<Integer> numbers) {
        Collections.sort(numbers);
    }
}
