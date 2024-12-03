package com.api.ecommerce.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PRODUCTS")
public class Product{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(nullable = false, unique = true)
  private String code;
  @Column(nullable = false, unique = true)
  private String name;
  @Column(nullable = false, unique = true)
  private String title;
  @Column(nullable = false)
  private String description;
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<Variant> variants;
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  @Column(nullable = false)
  private List<Details> details;
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  @Column(nullable = false)
  private List<Features> features;
  @Column(nullable = false)
  private Float price;
  @Column(nullable = false)
  private String category;
  @Column(nullable = false)
  private String image;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @Column(nullable = false)
  private LocalDateTime updatedAt;
  @Column(nullable = true)
  private Float discount;
}



