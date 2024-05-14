package br.com.fiap.productmanagement.controller;

import br.com.fiap.productmanagement.domain.usecase.SchedulingJobUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("product")
public class ProductController {

  private final SchedulingJobUseCase schedulingJobUseCase;

  public ProductController(SchedulingJobUseCase schedulingJobUseCase) {

    this.schedulingJobUseCase = schedulingJobUseCase;

  }

  @PostMapping()
  public String process(
          @RequestParam("file") MultipartFile file,
          @RequestParam(required = false) LocalDateTime date) throws Exception {

    schedulingJobUseCase.start(file, date);

    return "Produto processado com sucesso";

  }

}