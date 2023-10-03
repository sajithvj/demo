package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("books")
public class Books {
    @Id
    private String book_id;
    private String name;
}
