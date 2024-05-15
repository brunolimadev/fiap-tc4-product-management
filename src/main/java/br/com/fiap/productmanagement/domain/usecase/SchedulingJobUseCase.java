package br.com.fiap.productmanagement.domain.usecase;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface SchedulingJobUseCase {

  void start(MultipartFile file, LocalDateTime dateTime) throws Exception;

}