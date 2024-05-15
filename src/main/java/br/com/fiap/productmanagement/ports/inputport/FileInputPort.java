package br.com.fiap.productmanagement.ports.inputport;

import br.com.fiap.productmanagement.ports.exception.InputPortException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileInputPort {

  void upload(MultipartFile file) throws InputPortException;

  Path getTargetLocation(MultipartFile file) throws InputPortException;

}