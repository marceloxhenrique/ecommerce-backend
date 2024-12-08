package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.models.Product;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
  boolean existsByCode(String code);
  boolean existsByName(String name);
  boolean existsByCategory(String name);
  
  
}
