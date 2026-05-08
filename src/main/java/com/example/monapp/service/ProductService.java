// src/main/java/com/example/demo/service/ProductService.java
package com.example.monapp.service;

import com.example.monapp.model.Product;
import com.example.monapp.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service  // Dit à Spring : "gère ce bean pour moi"
public class ProductService {

    private final ProductRepository productRepository;

    // Injection de dépendance par constructeur (bonne pratique ✅)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Récupérer tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Récupérer un produit par ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Créer un produit
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Modifier un produit
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    // Supprimer un produit
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
