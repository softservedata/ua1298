package com.softserve.homwork09.hometask2;

/*
This TestClassOfCollectionOfNumbers class created for testing methods
with invalid values of array or arrays inputted manually
 */



import java.util.ArrayList;
import java.util.List;


public class TestClassOfCollectionOfNumbers extends CollectionOfNumbers{

    public TestClassOfCollectionOfNumbers(){
        super();
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

}
