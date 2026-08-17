package com.example.demo;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StrinPatternTest {
    public static void main (String[] arg){
        String user = "XX2909";
        System.out.println(Pattern.matches("[a-zA-Z]{1}[aA-zZ0-9]{5}", user));
        String accNo= "1234567810";
        String reclassScore="9TK";
        System.out.println(Pattern.matches("[0-9]{1}[A-Z]{1}", reclassScore));
        System.out.println(Pattern.matches("[a-zA-Z0-9]+", accNo.replaceAll("[\\-\\+\\.\\^:,]",""))+" Contains character");
        System.out.println(Pattern.matches("[0-9]{9}", accNo.replaceAll("[\\-\\+\\.\\^:,]","")));

        List<Integer> integerList = Arrays.asList(10,20,40,5,19,36,42,49,61);
        Optional<Integer> secondLargest= integerList.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
        secondLargest.ifPresentOrElse(
                num-> System.out.println(num),
                ()->System.out.println("np")
        );

        String input="programming";
        Map<Character,Long> duplicates = input.chars().mapToObj(c ->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        duplicates.entrySet().stream().filter(entry -> entry.getValue()>=2).forEach(entry ->System.out.println(entry.getKey() +":"+entry.getValue()));



    }
}
