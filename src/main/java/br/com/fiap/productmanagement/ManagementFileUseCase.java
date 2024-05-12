package br.com.fiap.productmanagement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ManagementFileUseCase {

  private final Path filePath;

  public ManagementFileUseCase(
          @Value("${file.upload-directory}") String uploadDirectory) {

    this.filePath = Paths.get(uploadDirectory);

  }

  public void upload(MultipartFile file) throws IOException {

    file.transferTo(getTargetLocation(file));

  }

  public Path getTargetLocation(MultipartFile file) {

    var fileName = StringUtils.cleanPath(file.getOriginalFilename());

    return filePath.resolve(fileName);

  }

}