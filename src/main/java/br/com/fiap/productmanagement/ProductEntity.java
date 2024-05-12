package br.com.fiap.productmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

  private String description;
  private String price;
  private String storeQuantity;
  private LocalDateTime createDateTime;

}