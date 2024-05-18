package br.com.fiap.productmanagement.adapter.ports.outputport;

import br.com.fiap.productmanagement.adapter.repositories.ProductRepository;
import br.com.fiap.productmanagement.mock.ProductModelMock;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

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

    fail();

  }

  @Test
  void shouldGetProductWithSuccess() {

    fail();

  }

  @Test
  void shouldThrowOutputPortExceptionTryingGetProduct() {

    fail();

  }

  @Test
  void shouldRemoveProductWithSuccess() {

    fail();

  }

  @Test
  void shouldThrowOutputPortExceptionTryingRemoveProduct() {

    fail();

  }

  @Test
  void shouldUpdateProductWithSuccess() {

    fail();

  }

  @Test
  void shouldThrowOutputPortExceptionTryingUpdateProduct() {

    fail();

  }


}