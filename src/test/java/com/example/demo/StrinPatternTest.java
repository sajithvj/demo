package com.example.demo;

import java.util.regex.Pattern;

public class StrinPatternTest {
    public static void main (String[] arg){
        String user = "XX2909";
        System.out.println(Pattern.matches("[a-zA-Z]{1}[aA-zZ0-9]{5}", user));
        String accNo= "1234567810";
        System.out.println(Pattern.matches("[a-zA-Z0-9]+", accNo.replaceAll("[\\-\\+\\.\\^:,]",""))+" Contains character");
        System.out.println(Pattern.matches("[0-9]{9}", accNo.replaceAll("[\\-\\+\\.\\^:,]","")));

    }
}
