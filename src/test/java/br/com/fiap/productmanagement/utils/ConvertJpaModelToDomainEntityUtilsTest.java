package br.com.fiap.productmanagement.utils;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.mock.ProductEntityMock;
import br.com.fiap.productmanagement.mock.ProductModelMock;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConvertJpaModelToDomainEntityUtilsTest {

  @Test
  void shouldCreateProductEntityGivenAProductModel() {

    //Arrange
    var productModel = ProductModelMock.get();
    var expectProductEntity = ProductEntityMock.get();

    //Act
    var productEntity = ConvertJpaModelToDomainEntityUtils.convert(productModel);

    //Assert
    assertThat(productEntity)
            .isInstanceOf(ProductEntity.class);

  }

}