package com.example.demo.service;

import com.example.demo.dto.TripsDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StringPracticalAdvice {
    @JsonProperty("message") String message;
    @JsonProperty("identifier") String identifier;

   public StringPracticalAdvice(String message,String identifier){
        this.message = message;
        this.identifier=identifier;
    }
}
