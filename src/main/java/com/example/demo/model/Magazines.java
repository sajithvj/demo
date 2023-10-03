package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("magazines")
public class Magazines {
    @Id
    private String magazine_id;
    private String name;
}
