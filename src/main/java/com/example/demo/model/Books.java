package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("books")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    @Id
    ObjectId id;
    private String book_id;
    private String name;
}
