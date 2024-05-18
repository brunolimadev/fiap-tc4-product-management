package br.com.fiap.productmanagement.adapter.ports.inputport;

import br.com.fiap.productmanagement.ports.exception.InputPortException;
import br.com.fiap.productmanagement.ports.inputport.FileInputPort;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static br.com.fiap.productmanagement.utils.MessageEnumUtils.FILE_GET_TARGET_LOCATION_INPUT_PORT_EXCEPTION;
import static br.com.fiap.productmanagement.utils.MessageEnumUtils.FILE_UPLOAD_INPUT_PORT_EXCEPTION;

public class FileInputPortAdapter implements FileInputPort {

  private final Path filePath;

  public FileInputPortAdapter(String uploadDirectory) {

    this.filePath = Paths.get(uploadDirectory);

  }

  @Override
  public void upload(MultipartFile file) throws InputPortException {

    try {

      file.transferTo(getTargetLocation(file));

    } catch (IOException e) {

      throw new InputPortException(FILE_UPLOAD_INPUT_PORT_EXCEPTION.getMessage());

    }

  }

  @Override
  public Path getTargetLocation(MultipartFile file) throws InputPortException {

    try {

      var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

      return filePath.resolve(fileName);

    } catch (Exception exception) {

      throw new InputPortException(FILE_GET_TARGET_LOCATION_INPUT_PORT_EXCEPTION.getMessage());

    }

  }

}