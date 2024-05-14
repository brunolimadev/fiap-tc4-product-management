package br.com.fiap.productmanagement.adapter.ports.inputport;

import br.com.fiap.productmanagement.ports.inputport.FileInputPort;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInputPortAdapter implements FileInputPort {

  private final Path filePath;

  public FileInputPortAdapter(String uploadDirectory) {

    this.filePath = Paths.get(uploadDirectory);

  }

  @Override
  public void upload(MultipartFile file) throws IOException {

    file.transferTo(getTargetLocation(file));

  }

  @Override
  public Path getTargetLocation(MultipartFile file) {

    var fileName = StringUtils.cleanPath(file.getOriginalFilename());

    return filePath.resolve(fileName);

  }

}