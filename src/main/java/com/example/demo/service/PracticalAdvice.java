package com.example.demo.service;

import com.example.demo.dto.BooksDto;
import com.example.demo.dto.TripsDto;
import com.example.demo.model.Books;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PracticalAdvice {
    @JsonProperty("message") String message;
    @JsonProperty("tripsIdentifier")
    TripsDto identifier;

    @JsonProperty("booksIdentifier")
    BooksDto identifier2;

    public PracticalAdvice(String message, TripsDto identifier) {
        this.message = message;
        this.identifier = identifier;
    }

    public PracticalAdvice(String message, BooksDto identifier2) {
        this.message = message;
        this.identifier2 = identifier2;
    }
}