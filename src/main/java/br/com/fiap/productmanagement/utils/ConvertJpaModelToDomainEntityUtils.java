package br.com.fiap.productmanagement.utils;

import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;
import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertJpaModelToDomainEntityUtils {

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