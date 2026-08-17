package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//Redis cache examples
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products implements Serializable {
   private Long id;
   private String name;
   private double price;
}
