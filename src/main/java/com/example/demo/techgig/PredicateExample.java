package com.example.demo.techgig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PredicateExample {
    public static void main (String[] args){
        List<String> names= new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Annie");
        names.add("Charles");
        names.add("Andrew");
        List<String> namesStartingWithA = names.stream()
                .filter(s -> s.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Names starting with A: " + namesStartingWithA);
    }
}
