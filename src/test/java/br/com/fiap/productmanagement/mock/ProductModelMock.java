package br.com.fiap.productmanagement.mock;

import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;

import java.util.Collections;
import java.util.List;

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

  public static List<ProductModel> getList() {

    return Collections.singletonList(get());

  }

}