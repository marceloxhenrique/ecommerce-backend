package com.api.ecommerce.controllers;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.ecommerce.dtos.ProductDto;
import com.api.ecommerce.models.Product;
import com.api.ecommerce.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ProductController {
  final ProductService productService;

  public ProductController(ProductService productService){
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto) {
    if (this.productService.existByCode(productDto.getCode())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product with the same code already exist");
    }
    if (this.productService.existsByName(productDto.getName())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product with the same name already exist");
    }
    try {
      productService.saveProduct(productDto);
      return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating product: " + e.getMessage());
    }  
  }

  @GetMapping
  public ResponseEntity<Object> getAllProducts(){
    try {
      return ResponseEntity.status(HttpStatus.OK).body(this.productService.findAll());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product found" + e.getMessage());
    }   
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getProductById(@PathVariable UUID id) {
    Optional<Product> productOptional = this.productService.findProductById(id);
    if(productOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteProuct(@PathVariable UUID id){
    Optional<Product> productOptional = this.productService.findProductById(id);
    if(productOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
    this.productService.deleteProduct(productOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("Product successfully deleted");
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateProduct(@PathVariable UUID id, @RequestBody ProductDto productDto) {
      Optional<Product> producOptional = this.productService.findProductById(id);
      if(producOptional.isEmpty()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
      }
      var product = producOptional.get();
      BeanUtils.copyProperties(productDto, product, "id", "code");
      product.setUpdatedAt(LocalDateTime.now());
      Product productUpdate = this.productService.save(product);
      return ResponseEntity.status(HttpStatus.OK).body(productUpdate);
  }
  
}
