package com.example.demo.service.impl;

import com.example.demo.model.Products;
import com.example.demo.repository.TripsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private TripsRepository tripsRepository;
    // 1. Fetching product (Checks 'products' cache namespace, keys will be generated like 'products::1')
    @Cacheable(value = "products", key="#id")
    public Products getProductsById(Long id){
        simulateSlowService();// Simulates a slow SQL database retrieval
       return new Products(id, "Gaming Laptop", 1200.00);
    }
    // 2. Updating product (Forces cache update to match the database state)
    @CachePut(value = "products", key = "#product.id")
    public Products updateProduct(Products product) {
        System.out.println("Updating product in database...");
        return product;
    }
    // 3. Deleting product (Removes key from Redis entirely to prevent stale reads)
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        System.out.println("Deleting product from database...");
    }
    private void simulateSlowService(){
        try{
            Thread.sleep(3000);
            //Calling direcltly to DB
            tripsRepository.findByBikeId(20);
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}
