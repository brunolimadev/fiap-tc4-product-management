package br.com.fiap.productmanagement.ports.outputport;

import br.com.fiap.productmanagement.ports.exception.OutputPortException;

import java.nio.file.Path;

public interface JobOutputPort {

  void run(String fileName, Path targetLocation) throws OutputPortException;

}