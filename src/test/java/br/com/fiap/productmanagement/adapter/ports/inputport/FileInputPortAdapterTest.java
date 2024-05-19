package br.com.fiap.productmanagement.adapter.ports.inputport;

import br.com.fiap.productmanagement.ports.exception.InputPortException;
import br.com.fiap.productmanagement.ports.inputport.FileInputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

import static br.com.fiap.productmanagement.utils.MessageEnumUtils.FILE_GET_TARGET_LOCATION_INPUT_PORT_EXCEPTION;
import static br.com.fiap.productmanagement.utils.MessageEnumUtils.FILE_UPLOAD_INPUT_PORT_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class FileInputPortAdapterTest {

  @Mock
  private MultipartFile multipartFile;

  private FileInputPort fileInputPort;
  private AutoCloseable openMocks;

  @BeforeEach
  void setup() {

    openMocks = MockitoAnnotations.openMocks(this);
    fileInputPort = new FileInputPortAdapter("src/test/resources/tmp");

  }

  @AfterEach
  void tearDown() throws Exception {

    openMocks.close();

  }

  @Test
  void shouldUploadFileGivenAFile() {

    //Arrange
    when(multipartFile.getOriginalFilename()).thenReturn("test-file");

    //Act & Assert
    assertDoesNotThrow(() -> fileInputPort.upload(multipartFile));

  }

  @Test
  void shouldThrowInputPortExceptionTryingUploadFileGivenAFile() throws IOException {

    //Arrange
    when(multipartFile.getOriginalFilename()).thenReturn("test-file");
    doThrow(IOException.class).when(multipartFile).transferTo(any(Path.class));

    //Act & Assert
    assertThatThrownBy(() -> fileInputPort.upload(multipartFile))
            .isInstanceOf(InputPortException.class)
            .hasMessage(FILE_UPLOAD_INPUT_PORT_EXCEPTION.getMessage());

  }

  @Test
  void shouldGetTargetLocationFileGivenAFile() {

    //Arrange
    when(multipartFile.getOriginalFilename()).thenReturn("test-file");

    //Act & Assert
    assertDoesNotThrow(() -> fileInputPort.getTargetLocation(multipartFile));

  }

  @Test
  void shouldThrowInputPortExceptionTryingGetTargetLocationFileGivenAFile() {

    //Arrange
    when(multipartFile.getOriginalFilename()).thenReturn(null);

    //Act & Assert
    assertThatThrownBy(() -> fileInputPort.getTargetLocation(multipartFile))
            .isInstanceOf(InputPortException.class)
            .hasMessage(FILE_GET_TARGET_LOCATION_INPUT_PORT_EXCEPTION.getMessage());

  }

}