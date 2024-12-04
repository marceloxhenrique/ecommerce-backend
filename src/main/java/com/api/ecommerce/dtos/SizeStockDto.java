package com.api.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SizeStockDto {
  @NotBlank(message = "Size of product is required")
  private String size;
  @NotNull(message = "Stock of product is required")
  private int stock;
}
