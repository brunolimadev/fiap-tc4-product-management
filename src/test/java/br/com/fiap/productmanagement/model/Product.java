package br.com.fiap.productmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

  private String description;
  private Double price;
  private Integer storeQuantity;

}