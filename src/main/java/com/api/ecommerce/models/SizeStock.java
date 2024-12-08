package com.api.ecommerce.models;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_PRODUCT_SIZE_STOCK")
public class SizeStock implements Serializable{
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id; 

  @JoinColumn(name = "variant_id")
  @ManyToOne
  @JsonBackReference
  private Variant variant;

  @Column(nullable = false)
  private String size;

  @Column(nullable = false)
  private int stock;
}
