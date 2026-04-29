// src/main/java/com/example/demo/model/Product.java
package com.example.monapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity  // Dit à Spring : "cette classe = une table en BDD"
@Table(name = "products")
public class Product {

    @Id  // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    private String description;

    // --- Constructeurs ---
    public Product() {}  // Obligatoire pour JPA !

    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}