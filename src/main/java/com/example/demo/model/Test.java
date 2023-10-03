package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document("test_1")
public class Test {
    @Id
    private String id;
    private String name;
    private String desc;

}
