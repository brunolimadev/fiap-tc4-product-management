package br.com.fiap.productmanagement.utils;

import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;
import br.com.fiap.productmanagement.domain.entities.ProductEntity;

public class ConvertJpaModelToDomainEntityUtils {

  private ConvertJpaModelToDomainEntityUtils() {

    throw new IllegalStateException("Ocorreu um erro ao instanciar a classe");

  }

  public static ProductEntity convert(ProductModel productModel) {



    return ProductEntity
            .builder()
              .id(productModel.getId())
              .description(productModel.getDescription())
              .price(productModel.getPrice())
              .storeQuantity(productModel.getStoreQuantity())
              .createDateTime(productModel.getCreateDateTime())
              .updateDateTime(productModel.getUpdateDateTime())
            .build();

  }

}