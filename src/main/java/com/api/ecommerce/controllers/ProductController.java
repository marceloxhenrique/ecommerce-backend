package com.api.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.dtos.ProductDto;
import com.api.ecommerce.services.ProductService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/products")
public class ProductController {
  final ProductService productService;

  public ProductController(ProductService productService){
    this.productService = productService;
  }

  @GetMapping
  public String getAllProducts(){
    return "Hello World";
  }

  @PostMapping
  public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto) {
    // var newProduct = new Product();
    // BeanUtils.copyProperties(productDto, newProduct);
    // newProduct.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
    // newProduct.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
    
    try {
            productService.save(productDto);
            return ResponseEntity.ok("Product created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product: " + e.getMessage());
        }
        
      
      
  }
  
}
