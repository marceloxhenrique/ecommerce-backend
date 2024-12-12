package com.api.ecommerce.services;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.ecommerce.dtos.ProductDto;
import com.api.ecommerce.models.Product;
import com.api.ecommerce.models.SizeStock;
import com.api.ecommerce.repositories.ProductRepository;
import com.api.ecommerce.repositories.SizeStockRepository;
import com.api.ecommerce.repositories.VariantRepository;

import jakarta.transaction.Transactional;

import com.api.ecommerce.models.Variant;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final VariantRepository variantRepository;
  private final SizeStockRepository sizeStockRepository;

  public ProductService(
    ProductRepository productRepository,
    VariantRepository variantRepository,
    SizeStockRepository sizeStockRepository
    ){
    this.productRepository = productRepository;
    this.variantRepository = variantRepository;
    this.sizeStockRepository = sizeStockRepository;
  }

  public List<Product> findAll(){
    List<Product> products = this.productRepository.findAll();
    return products;
  }
  
  @Transactional
  public Product save(Product product){
    this.validateProduct(product);
    return this.productRepository.save(product);
  }

  public void saveProduct(ProductDto productRequest){
    this.validateProduct(productRequest);
    Product product = new Product();
    BeanUtils.copyProperties(productRequest, product);
    product.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
    product.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
    Product savedProduct = this.productRepository.save(product);

    productRequest.getVariants().forEach(variantRequest -> {
      Variant variantEntity = new Variant();
      variantEntity.setProduct(savedProduct);
      variantEntity.setColor(variantRequest.getColor());
      Variant savedVariant = variantRepository.save(variantEntity);

      variantRequest.getSizeStock().forEach(sizeStockRequest -> {
        SizeStock sizeStockEntity = new SizeStock();
        sizeStockEntity.setVariant(savedVariant);
        sizeStockEntity.setSize(sizeStockRequest.getSize());
        sizeStockEntity.setStock(sizeStockRequest.getStock());
        sizeStockRepository.save(sizeStockEntity);
        });
    });
  }

  private <T> void validateProduct(T entity){
    for(Field field: entity.getClass().getDeclaredFields()){
      field.setAccessible(true);
      try {
        Object value = field.get(entity);
        if(value == null){
          throw new DataIntegrityViolationException(field.getName() + " cannot be null or empty");
        }
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public Optional<Product> findProductById(UUID productId){
    return this.productRepository.findById(productId);
  }
  @Transactional
  public void deleteProduct(Product product){
    this.productRepository.delete(product);
  }
  public boolean existByCode(String code){
    return this.productRepository.existsByCode(code);
  }
  public boolean existsByName(String name){
    return this.productRepository.existsByName(name);
  }
  public boolean existsByCategory(String category){
    return this.productRepository.existsByCategory(category);
  }

}
