package com.api.ecommerce.dtos;

import java.util.List;

import com.api.ecommerce.enums.Colors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VariantDto {
  @NotNull(message = "Vairant color is required")
  private Colors color;
  @NotEmpty(message = "Variant size/stock is required")
  private List<SizeStockDto> sizeStock;
}
