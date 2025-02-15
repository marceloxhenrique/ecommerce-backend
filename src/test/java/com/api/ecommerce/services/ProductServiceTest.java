package com.api.ecommerce.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.context.ActiveProfiles;

import com.api.ecommerce.dtos.ProductDto;
import com.api.ecommerce.dtos.SizeStockDto;
import com.api.ecommerce.dtos.VariantDto;
import com.api.ecommerce.enums.Colors;
import com.api.ecommerce.models.Product;
import com.api.ecommerce.repositories.ProductRepository;
import com.api.ecommerce.repositories.SizeStockRepository;
import com.api.ecommerce.repositories.VariantRepository;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {
  @Mock
  private ProductRepository productRepository;
  @Mock
  private VariantRepository variantRepository;
  @Mock 
  private SizeStockRepository sizeStockRepository;
  
  @InjectMocks
  private  ProductService productService;
  
  private ProductDto productTest;

  @BeforeEach
  public void setUp(){
    ArrayList<String> detailsTest = new ArrayList<>();
    detailsTest.add("One detail");
    detailsTest.add("Two details");

    List<SizeStockDto> sizeStockList = new ArrayList<>();
    SizeStockDto sizeStockDto = new SizeStockDto();
    sizeStockDto.setSize("45");
    sizeStockDto.setStock(11);
    sizeStockList.add(sizeStockDto);

    List<VariantDto> variantsList = new ArrayList<>();
    VariantDto variantTest = new VariantDto();
    variantTest.setColor(Colors.RED);
    variantTest.setSizeStock(sizeStockList);
    
    productTest = new ProductDto();
    productTest.setCode("PRD1234");
    productTest.setName("Air Test");
    productTest.setTitle("random title");
    productTest.setDescription("My description");
    productTest.setVariants(variantsList);
    productTest.setDetails(detailsTest);
    productTest.setPrice((float) 150.99);
    productTest.setCategory("Running shoew");
    productTest.setImage("shoes1.png");
    productTest.setCreatedAt(LocalDateTime.now());
    productTest.setUpdatedAt(LocalDateTime.now());
    productTest.setDiscount((float) 10);

  }

  @Test
  @DisplayName("Should save Product")
  void testSaveProduct(){
    when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
      Product product = invocation.getArgument(0);
      product.setId(UUID.randomUUID());
      product.setCode("PRD1234");
      return product;
    });

    Product savedProduct = productService.saveProduct(productTest);

    verify(productRepository, times(1)).save(any(Product.class));
    
    assertThat(savedProduct.getId()).isNotNull();
    assertThat(savedProduct.getCode()).isEqualTo("PRD1234");
  }

  @Test
  @DisplayName("Should find product by ID")
  void testFindProductById(){
    Product product = new Product();
      product.setId(UUID.randomUUID());
      product.setCode("PRD1234");
    when(productRepository.findById(any())).thenReturn(Optional.of(product));

    Optional<Product> productById = productService.findProductById(product.getId());

    verify(productRepository, times(1)).findById(product.getId());
    assertThat(productById.get().getId()).isEqualTo(product.getId());
    assertThat(productById.get().getCode()).isEqualTo("PRD1234");
  }
  
  @Test
  @DisplayName("Should get all products available")
  void testGetallProducts(){
    List<Product> myProductList = new ArrayList<>();
    Product product1 = new Product();
    product1.setId(UUID.randomUUID());
    product1.setCode("PRD1234");
    Product product2 = new Product();
    product2.setId(UUID.randomUUID());
    product2.setCode("PRD2345");
    myProductList.add(product1);
    myProductList.add(product2);

    when(productRepository.findAll()).thenReturn(myProductList);

    List<Product> productList = productService.findAll();

    verify(productRepository, times(1)).findAll();
    assertThat(productList.size()).isEqualTo(2);
    assertThat(productList.get(0).getCode()).isEqualTo("PRD1234");
  }
  
}