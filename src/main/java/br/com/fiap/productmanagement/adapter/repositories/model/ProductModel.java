package br.com.fiap.productmanagement.adapter.repositories.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private String price;

  @Column(name = "store_quantity")
  private String storeQuantity;

  @Column(name = "create_datetime")
  private LocalDateTime createDateTime;

}