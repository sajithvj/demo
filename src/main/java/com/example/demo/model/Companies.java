package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("test_companies")
public class Companies {
    @Id
    private String id;
    private String name;
    private Integer number_of_employees;
    private Integer founded_year;
    private Integer founded_month;
    private Integer founded_day;
    private String  email_address;
    private String phone_number;
    private String description;
    private LocalDateTime  created_at;
    private String updated_at;
    private String overview;
    private Object image;
    private List<Products> products;

}
