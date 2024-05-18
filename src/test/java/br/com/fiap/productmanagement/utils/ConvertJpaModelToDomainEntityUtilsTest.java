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
    var productEntityConverted = ConvertJpaModelToDomainEntityUtils.convert(productModel);

    //Assert
    assertThat(productEntityConverted)
            .isInstanceOf(ProductEntity.class);

    assertThat(productEntityConverted.getId()).isEqualTo(expectProductEntity.getId());
    assertThat(productEntityConverted.getDescription()).isEqualTo(expectProductEntity.getDescription());
    assertThat(productEntityConverted.getStoreQuantity()).isEqualTo(expectProductEntity.getStoreQuantity());
    assertThat(productEntityConverted.getCreateDateTime()).isEqualTo(expectProductEntity.getCreateDateTime());
    assertThat(productEntityConverted.getUpdateDateTime()).isEqualTo(expectProductEntity.getUpdateDateTime());

  }

}