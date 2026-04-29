// src/main/java/com/example/demo/controller/ProductController.java
package com.example.monapp.controller;

import com.example.monapp.model.Product;
import com.example.monapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController         // Controller qui retourne du JSON
@RequestMapping("/api/products")  // Préfixe commun à tous les endpoints
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products → liste tous les produits
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);  // 200 OK + la liste
    }

    // GET /api/products/{id} → récupère un produit par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(product))   // 200 si trouvé
                .orElse(ResponseEntity.notFound().build());   // 404 si pas trouvé
    }

    // POST /api/products → crée un nouveau produit
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);  // 201 Created
    }
}