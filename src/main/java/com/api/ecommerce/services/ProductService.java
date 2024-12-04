package com.api.ecommerce.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.springframework.stereotype.Service;

import com.api.ecommerce.dtos.ProductDto;
import com.api.ecommerce.models.Details;
import com.api.ecommerce.models.Features;
import com.api.ecommerce.models.Product;
import com.api.ecommerce.models.SizeStock;
import com.api.ecommerce.repositories.DetailsRepository;
import com.api.ecommerce.repositories.FeaturesRepository;
import com.api.ecommerce.repositories.ProductRepository;
import com.api.ecommerce.repositories.SizeStockRepository;
import com.api.ecommerce.repositories.VariantRepository;
import com.api.ecommerce.models.Variant;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final VariantRepository variantRepository;
  private final SizeStockRepository sizeStockRepository;
  private final DetailsRepository detailsRepository;
  private final FeaturesRepository featuresRepository;

  public ProductService(
    ProductRepository productRepository,
    VariantRepository variantRepository,
    SizeStockRepository sizeStockRepository,
    DetailsRepository detailsRepository,
    FeaturesRepository featuresRepository
    ){
    this.productRepository = productRepository;
    this.variantRepository = variantRepository;
    this.sizeStockRepository = sizeStockRepository;
    this.detailsRepository = detailsRepository;
    this.featuresRepository = featuresRepository;
  }

  public List<Product> findAll(){
    return this.productRepository.findAll();
  }

  
  public void save(ProductDto productRequest){
    Product product = new Product();
    product.setCode(productRequest.getCode());
    product.setName(productRequest.getName());
    product.setTitle(productRequest.getTitle());
    product.setDescription(productRequest.getDescription());
    product.setPrice(productRequest.getPrice());
    product.setCategory(productRequest.getCategory());
    product.setImage(productRequest.getImage());
    product.setDiscount(productRequest.getDiscount());
    // parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    product.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
    product.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
    // BeanUtils.copyProperties(productRequest, product);
    Product savedProduct = this.productRepository.save(product);

    productRequest.getDetails().forEach(detail -> {
            Details detailEntity = new Details();
            detailEntity.setProduct(savedProduct);
            detailEntity.setDetails(detail);
            detailsRepository.save(detailEntity);
        });

        // 3. Save Features
        productRequest.getFeatures().forEach(feature -> {
            Features featureEntity = new Features();
            featureEntity.setProduct(savedProduct);
            featureEntity.setFeatures(feature);
            featuresRepository.save(featureEntity);
        });

        // 4. Save Variants and SizeStock
        productRequest.getVariants().forEach(variantRequest -> {
            Variant variantEntity = new Variant();
            variantEntity.setProduct(savedProduct);
            variantEntity.setColor(variantRequest.getColor());
            Variant savedVariant = variantRepository.save(variantEntity);

            variantRequest.getSizeStockDto().forEach(sizeStockRequest -> {
                SizeStock sizeStockEntity = new SizeStock();
                sizeStockEntity.setVariant(savedVariant);
                sizeStockEntity.setSize(sizeStockRequest.getSize());
                sizeStockEntity.setStock(sizeStockRequest.getStock());
                sizeStockRepository.save(sizeStockEntity);
            });
        });
  }


}
