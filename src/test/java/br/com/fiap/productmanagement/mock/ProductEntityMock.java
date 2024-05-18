package br.com.fiap.productmanagement.mock;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;

import java.util.Collections;
import java.util.List;

public class ProductEntityMock {

  public static ProductEntity get() {

    return ProductEntity
            .builder()
              .id(1L)
              .description("Camiseta da Adidas GG")
              .price("20.90")
              .storeQuantity("2")
            .build();

  }

  public static List<ProductEntity> getList() {

    return Collections.singletonList(get());

  }

}