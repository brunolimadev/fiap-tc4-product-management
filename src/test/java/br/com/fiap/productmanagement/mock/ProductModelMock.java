package br.com.fiap.productmanagement.mock;

import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;

public class ProductModelMock {

  public static ProductModel get() {

    return ProductModel
            .builder()
              .id(1L)
              .description("Camiseta da Adidas GG")
              .price("20.90")
              .storeQuantity("2")
            .build();

  }

}