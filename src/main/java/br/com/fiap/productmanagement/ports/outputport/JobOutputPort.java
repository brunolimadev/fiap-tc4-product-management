package br.com.fiap.productmanagement.ports.outputport;

import org.springframework.batch.core.JobExecutionException;

import java.nio.file.Path;

public interface JobOutputPort {

  void run(String fileName, Path targetLocation) throws JobExecutionException;

}