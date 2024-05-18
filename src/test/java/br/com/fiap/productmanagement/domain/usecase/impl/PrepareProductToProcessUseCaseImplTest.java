package br.com.fiap.productmanagement.domain.usecase.impl;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.domain.exception.UseCaseException;
import br.com.fiap.productmanagement.domain.usecase.PrepareProductToProcessUseCase;
import br.com.fiap.productmanagement.mock.ProductEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.fiap.productmanagement.utils.MessageEnumUtils.PREPARE_PRODUCT_TO_PROCESS_USE_CASE_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PrepareProductToProcessUseCaseImplTest {

  private PrepareProductToProcessUseCase prepareProductToProcessUseCase;

  @BeforeEach
  void setup() {

    prepareProductToProcessUseCase = new PrepareProductToProcessUseCaseImpl();

  }

  @Test
  void shouldPrepareProductToProcessGivenAProductEntity() throws Exception {

    //Arrange
    var productEntityRequest = ProductEntityMock.get();
    productEntityRequest.setPrice("200,00");

    var priceExpect = "200.00";

    //Act
    var productEntityResult = prepareProductToProcessUseCase.process(productEntityRequest);

    //Assert
    assertThat(productEntityResult)
            .isNotNull()
            .isInstanceOf(ProductEntity.class);

    assertThat(productEntityResult.getPrice())
            .isNotNull()
            .isNotNull().isEqualTo(priceExpect);

  }

  @Test
  void shouldThrowUseCaseExceptionTryingPrepareProductToProcessGivenAProductEntityWithNullPrice() throws Exception {

    //Arrange
    var productEntityRequest = ProductEntityMock.get();
    productEntityRequest.setPrice(null);

    //Act & Assert
    assertThatThrownBy(() -> prepareProductToProcessUseCase.process(productEntityRequest))
            .isInstanceOf(UseCaseException.class)
            .hasMessage(PREPARE_PRODUCT_TO_PROCESS_USE_CASE_EXCEPTION.getMessage());

  }

}