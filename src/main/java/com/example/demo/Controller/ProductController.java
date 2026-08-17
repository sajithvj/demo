package com.example.demo.Controller;

import com.example.demo.model.Products;
import com.example.demo.service.impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductsById(@PathVariable Long id){
        return  new ResponseEntity<>(productService.getProductsById(id),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Products> updateProduct(@RequestBody(required = true) Products products){
        return new ResponseEntity<>(productService.updateProduct(products),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted and cache cleared!",HttpStatus.NO_CONTENT);
    }
}
