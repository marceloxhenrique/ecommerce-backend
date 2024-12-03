package com.api.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.ecommerce.models.Product;
import com.api.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
  final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository){
    this.productRepository = productRepository;
  }

  public List<Product> findAll(){
    return this.productRepository.findAll();
  }



}
