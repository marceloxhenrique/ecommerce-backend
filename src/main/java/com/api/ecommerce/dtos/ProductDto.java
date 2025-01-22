package com.api.ecommerce.dtos;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
  
  @NotBlank(message = "Product code is required")
  private String code;

  @NotBlank(message = "Product name is required")
  private String name;

  @NotBlank(message = "Product title is required")
  private String title;

  @NotBlank(message = "Product description is required")
  private String description;

  @NotEmpty(message = "Product variant is required")
  private List<VariantDto> variants;

  @NotEmpty(message = "Product details is required")
  private List<String> details;

  @NotNull(message = "Product price is required")
  private Float price;

  @NotBlank(message = "Product category is required")
  private String category;

  @NotBlank(message = "Product image is required")
  private String image;

  @NotNull(message = "Product create date is required")
  private LocalDateTime createdAt;

  @NotNull(message = "Product update is required")
  private LocalDateTime updatedAt;

  @NotNull(message = "Product discount is required")
  private Float discount;
}
