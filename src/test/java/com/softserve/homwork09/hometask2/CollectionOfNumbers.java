package com.softserve.homwork09.hometask2;

import java.util.*;

public class CollectionOfNumbers {

    private List<Integer> numbers;


    public CollectionOfNumbers(){
        this.numbers = new ArrayList<>();
    }


    public void generateRandomNumbers(int count, int maxValue) {
        Random random = new Random();
        numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(maxValue) + 1);
        }
    }


    private void checkIfEmpty() {
        if (numbers == null || numbers.isEmpty()) {
            throw new NoSuchElementException("The list is empty or has not been initialized.");
        }
    }

    public List<Integer> getNumbers() {
        checkIfEmpty();
        return numbers;
    }


    public int findMin() {
        checkIfEmpty();
        return Collections.min(numbers);
    }


    public int findMax() {
        checkIfEmpty();
        return Collections.max(numbers);
    }


    public double calculateAverage() {
        checkIfEmpty();
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.size();
    }

    public List<Integer> removeAllEven(){
        checkIfEmpty();
        List<Integer> numbersWithoutEven = new ArrayList<>();
        for (int num : numbers){
            if (num % 2 != 0){
                numbersWithoutEven.add(num);
            }
        }
        return numbersWithoutEven;
    }

    public boolean checkGivenNumber(int givenNumber){
        checkIfEmpty();
        if (numbers.contains(givenNumber)) {
            return true;
        } else {

            return false;
        }
    }

    public void printCheckingGivenNumberMessage(int givenNumber){
        if (checkGivenNumber(givenNumber)) {
            System.out.println("The number "
                    + givenNumber + " is present in the collection");
        } else {
            System.out.println("The number "
                    + givenNumber + " is absent in the collection");
        }
    }

    public List<Integer> sortByAscending(){
        checkIfEmpty();
//        numbers.sort(Integer::compareTo);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }



}

