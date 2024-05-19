package br.com.fiap.productmanagement.adapter.ports.outputport;

import br.com.fiap.productmanagement.adapter.repositories.ProductRepository;
import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;
import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.mock.ProductEntityMock;
import br.com.fiap.productmanagement.mock.ProductModelMock;
import br.com.fiap.productmanagement.ports.exception.OutputPortException;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static br.com.fiap.productmanagement.utils.MessageEnumUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductManagementOutputPortAdapterTest {

  @Mock
  private ProductRepository productRepository;

  private ProductManagementOutputPort productManagementOutputPort;
  private AutoCloseable openMocks;

  @BeforeEach
  void setup() {

    openMocks = MockitoAnnotations.openMocks(this);
    productManagementOutputPort = new ProductManagementOutputPortAdapter(productRepository);

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldGetProductsWithSuccess() {

    //Arrange
    var productModelList = ProductModelMock.getList();
    when(productRepository.findAll()).thenReturn(productModelList);

    //Act
    var response = productManagementOutputPort.getProducts();

    //Assert
    assertThat(response)
            .isNotNull()
            .isNotEmpty()
            .asList()
            .hasSize(productModelList.size());

  }

  @Test
  void shouldThrowOutputPortExceptionTryingGetProducts() {

    //Arrange
    when(productRepository.findAll()).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> productManagementOutputPort.getProducts())
            .isInstanceOf(OutputPortException.class)
            .hasMessage(PRODUCT_MANAGEMENT_GET_PRODUCTS_OUTPUT_PORT_EXCEPTION.getMessage());
    verify(productRepository, times(1)).findAll();

  }

  @Test
  void shouldGetProductWithSuccess() {

    //Arrange
    var productModel = ProductModelMock.get();
    var productEntity = ProductEntityMock.get();
    var productEntityId = productEntity.getId();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(productModel));

    //Act
    var response = productManagementOutputPort.getProduct(productEntityId);

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(ProductEntity.class);

    assertThat(response.getId()).isEqualTo(productEntityId);
    verify(productRepository, times(1)).findById(anyLong());

  }

  @Test
  void shouldThrowOutputPortExceptionTryingGetProduct() {

    //Arrange
    var productEntity = ProductEntityMock.get();
    var productEntityId = productEntity.getId();
    when(productRepository.findById(anyLong())).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> productManagementOutputPort.getProduct(productEntityId))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(PRODUCT_MANAGEMENT_GET_PRODUCT_OUTPUT_PORT_EXCEPTION.getMessage());
    verify(productRepository, times(1)).findById(anyLong());

  }

  @Test
  void shouldRemoveProductWithSuccess() {

    //Arrange
    var productModel = ProductModelMock.get();
    var productEntity = ProductEntityMock.get();
    var productEntityId = productEntity.getId();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(productModel));
    doNothing().when(productRepository).deleteById(anyLong());

    //Act
    var response = productManagementOutputPort.removeProduct(productEntityId);

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(ProductEntity.class);

    assertThat(response.getId()).isEqualTo(productEntityId);

  }

  @Test
  void shouldThrowOutputPortExceptionTryingRemoveProduct() {

    //Arrange
    var productModel = ProductModelMock.get();
    var productEntity = ProductEntityMock.get();
    var productEntityId = productEntity.getId();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(productModel));
    doThrow(RuntimeException.class).when(productRepository).deleteById(anyLong());

    //Act & Assert
    assertThatThrownBy(() -> productManagementOutputPort.removeProduct(productEntityId))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(PRODUCT_MANAGEMENT_REMOVE_PRODUCT_OUTPUT_PORT_EXCEPTION.getMessage());

  }

  @Test
  void shouldUpdateProductWithSuccess() {

    //Arrange
    var productModel = ProductModelMock.get();
    var productEntity = ProductEntityMock.get();
    var productEntityId = productEntity.getId();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(productModel));
    when(productRepository.save(any(ProductModel.class))).thenReturn(productModel);

    //Act
    var response = productManagementOutputPort.updateProduct(productEntityId, ProductEntityMock.get());

    //Assert
    assertThat(response)
            .isNotNull()
            .isInstanceOf(ProductEntity.class);

    assertThat(response.getId()).isEqualTo(productEntityId);

  }

  @Test
  void shouldThrowOutputPortExceptionTryingUpdateProduct() {

    //Arrange
    var productModel = ProductModelMock.get();
    var productEntity = ProductEntityMock.get();
    var productEntityId = productEntity.getId();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(productModel));
    when(productRepository.save(any(ProductModel.class))).thenThrow(RuntimeException.class);

    //Act & Assert
    assertThatThrownBy(() -> productManagementOutputPort.updateProduct(productEntityId, productEntity))
            .isInstanceOf(OutputPortException.class)
            .hasMessage(PRODUCT_MANAGEMENT_UPDATE_PRODUCT_OUTPUT_PORT_EXCEPTION.getMessage());

  }


}