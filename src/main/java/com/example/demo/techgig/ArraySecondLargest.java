package com.example.demo.techgig;

import java.util.Arrays;
import java.util.Comparator;

public class ArraySecondLargest {
    public static void main(String[] args){
        int array[]={1,3,6,0,2,9,5,4};
        int secondLargest= Arrays.stream(array).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(secondLargest);
    }
}
