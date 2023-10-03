package com.example.demo.repository;

import com.example.demo.model.Trips;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TripsRepository extends MongoRepository<Trips,String> {
    @Query(value =  "{bikeid:?0}")
    List<Trips> findByBikeId(Integer bikeId);
}
