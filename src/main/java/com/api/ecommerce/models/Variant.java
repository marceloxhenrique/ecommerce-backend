package com.api.ecommerce.models;

import java.util.List;
import java.util.UUID;

import com.api.ecommerce.enums.Colors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PRODUCTS_VARIANTS")
public class Variant {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(nullable = false)
  private Colors color;
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
  @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
  private List<SizeStock> sizeStock;
}



