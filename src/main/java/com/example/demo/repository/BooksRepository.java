package com.example.demo.repository;

import com.example.demo.model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Books,String> {
}
