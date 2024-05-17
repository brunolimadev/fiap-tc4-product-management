package br.com.fiap.productmanagement.domain.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

  private Long id;
  private String description;
  private String price;
  private String storeQuantity;
  private LocalDateTime createDateTime;
  private LocalDateTime updateDateTime;

}