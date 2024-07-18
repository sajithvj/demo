package com.example.demo.techgig;

import com.example.demo.model.Books;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingListExample {
    public static void main(String arg[]){
         List<String>s = Arrays.asList(new String("one"),new String("one"),new String("two"),new String("5"));
        Map<String,List<String>> groups=s.stream().collect(Collectors.groupingBy(String::valueOf));
        groups.forEach((s1,g1)->{
            System.out.println("Grpu: " + s1);
            g1.forEach(System.out::println);
            System.out.println();
        });
    }



}