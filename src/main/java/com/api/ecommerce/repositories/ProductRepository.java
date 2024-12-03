package com.api.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
  
}
