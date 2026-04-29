// src/main/java/com/example/demo/repository/ProductRepository.java
package com.example.monapp.repository;
// src/main/java/com/example/demo/repository/ProductRepository.java
import com.example.monapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository te donne GRATUITEMENT :
    // findAll(), findById(), save(), deleteById()...
    // Tu n'as rien à écrire ici pour l'instant !
}
