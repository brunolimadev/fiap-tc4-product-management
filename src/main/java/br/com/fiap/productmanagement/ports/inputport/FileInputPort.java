package br.com.fiap.productmanagement.ports.inputport;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileInputPort {

  void upload(MultipartFile file) throws IOException;

  Path getTargetLocation(MultipartFile file);

}