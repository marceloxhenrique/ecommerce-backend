package com.api.ecommerce.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PRODUCT_SIZE_STOCK")
public class SizeStock {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID sizeStockId;
  @ManyToOne
  @JoinColumn(name = "variant_id", nullable = false)
  private Variant variant;
  @Column(nullable = false)
  private String size;
  @Column(nullable = false)
  private int Stock;
}
