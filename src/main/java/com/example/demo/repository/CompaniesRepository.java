package com.example.demo.repository;

import com.example.demo.model.Companies;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CompaniesRepository extends MongoRepository<Companies,String> {
    @Query(value = "{name:?0}")
    List<Companies>getAllCompaniesByName(String name);

}
