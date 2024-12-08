package com.api.ecommerce.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.api.ecommerce.enums.Colors;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.ToString;

@Data
@Entity
@Table(name = "TB_PRODUCTS_VARIANTS")
public class Variant implements Serializable{
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private Colors color;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  @JsonBackReference
  @ToString.Exclude
  private Product product;
  
  @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<SizeStock> sizeStock;
}