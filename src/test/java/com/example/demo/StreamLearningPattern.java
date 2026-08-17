package com.example.demo;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamLearningPattern {
    public static void main(String[] arg) {
        String sentence_1 = "I am learning stream example";
        //first character in a sentence
        Character ch=sentence_1.chars().mapToObj(c->(char)c).filter(Character::isLetter).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())).entrySet().stream().map(Map.Entry::getKey).findFirst().orElse(null);
        System.out.println("First chatacter "+ch);
        //Maximum length word in a sentensce
        Arrays.stream(sentence_1.split(" ")).max(Comparator.comparing(String::length)).stream().forEach(System.out::println);
        Arrays.stream(sentence_1.split(" ")).reduce((x1, x2)->x1.length()>x2.length()?x1:x2).ifPresent(System.out::println);
        //Manipulation in a word
        String input="programming";
        input.chars().mapToObj(c->(char)c).distinct().forEach(System.out::print);
        System.out.println();
        Character prgm = input.chars().mapToObj(c->(char)c).distinct().sorted(Comparator.reverseOrder()).skip(0).findFirst().get();
        System.out.println(prgm);
        Long integerInput= Long.valueOf(123796548);

        Long outputLong =Long.valueOf(integerInput.toString().chars().mapToObj(c->String.valueOf((char)c)).sorted().collect(Collectors.joining()));
        System.out.println(outputLong);

        List<Integer> longList = Arrays.asList(2,0,1,5,3,6,9,10);
        Integer outputInt= longList.stream().sorted()    // Multiplies the running total by 10 (or 100 for '10') and adds the next number
                .reduce(0, (total, element) -> total * (element == 10 ? 100 : 10) + element);
        System.out.println(outputInt);
// Reverse order in a String
        String ans = Arrays.stream(sentence_1.split(" ")).sorted(Comparator.comparing(String::length).reversed()).skip(1).findFirst().get();
       System.out.println(ans);
       //Using comparator
       String ans2= String.valueOf(Arrays.stream(sentence_1.split(" ")).collect(Collectors.toList()).stream().sorted((x1, x2)->x1.compareTo(x2)).reduce(String::concat).get());
       System.out.println(ans2);
//Find the no:of duplicates in a sentence
        Map<Boolean,List<String>> duplicatesP= Arrays.stream(sentence_1.split(" ")).collect(Collectors.partitioningBy(x->x.length()%2==0));
        duplicatesP.entrySet().stream().forEach(entry->System.out.print(entry.getKey()+":"+entry.getValue()));
        System.out.println();
        Map<String,Long> duplicatesG= Arrays.stream(sentence_1.split(" ")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        duplicatesG.entrySet().stream().forEach(entry->System.out.print(entry.getKey() +":"+entry.getValue()));
//Find
        Arrays.stream(sentence_1.split(" ")).filter(x->x.replaceAll("[^aeiouAEIOU]","x").length()==1).forEach(System.out::println);
        List<Integer> integerList = Arrays.asList(10,20,40,5,19,36,42,49,61);
       List<Integer> xotr=integerList.stream().sorted((c1,c2)->-c1.compareTo(c2)).collect(Collectors.toList());
       Integer intmax = integerList.stream().max(Comparator.naturalOrder()).get();
       System.out.println(xotr);
        List<List<Integer>> checkedLiist=integerList.stream().collect(Collectors.partitioningBy(x->x%2==0,Collectors.toList())).entrySet().stream().map(x->x.getValue()).collect(Collectors.toList());
        System.out.println(checkedLiist);
        String prg ="abcdabcdefg";
        Map<Character,Long> duplicateString = prg.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().filter(p->p.getValue()>1).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        System.out.println(duplicateString);
        duplicateString.forEach((k,v)->System.out.println(k+"->>"+v));
        List<List<String>> name= Arrays.asList(Arrays.asList("John","Martin"),Arrays.asList("ram","sethu","jack"));
        name.parallelStream().flatMap(x->x.stream()).distinct().sorted().collect(Collectors.toList()).stream().filter(x->x.toLowerCase().charAt(0)=='j').forEach(System.out::println);
        ConnectBasicTest connectBasicTest = new ConnectBasicTest();
        connectBasicTest.connectBasic();
    }


}
