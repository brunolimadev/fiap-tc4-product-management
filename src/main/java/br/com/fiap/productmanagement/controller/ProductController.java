package br.com.fiap.productmanagement.controller;

import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;
import br.com.fiap.productmanagement.domain.exception.UseCaseException;
import br.com.fiap.productmanagement.domain.usecase.SchedulingJobUseCase;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("product")
public class ProductController {

  private final SchedulingJobUseCase schedulingJobUseCase;
  private final ProductManagementOutputPort productManagementOutputPort;

  public ProductController(
          SchedulingJobUseCase schedulingJobUseCase,
          ProductManagementOutputPort productManagementOutputPort
  ) {

    this.schedulingJobUseCase = schedulingJobUseCase;
    this.productManagementOutputPort = productManagementOutputPort;

  }

  @PostMapping
  public String processProduct(
          @RequestParam("file") MultipartFile file,
          @RequestParam(required = false) LocalDateTime date) throws UseCaseException {

    schedulingJobUseCase.start(file, date);

    return "Produto processado com sucesso";

  }

  @GetMapping
  public ResponseEntity<Object> getAllProducts() {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.getProducts());

  }

  @GetMapping(value = "{id}")
  public ResponseEntity<Object> getProduct(@PathVariable("id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.getProduct(id));

  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<Object> removeProduct(@PathVariable("id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.removeProduct(id));

  }

  @PutMapping(value = "/{id}/stocks")
  public ResponseEntity<Object> updateProduct(
          @PathVariable("id") Long id,
          @RequestBody ProductModel productModel
  ) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.updateProduct(id, productModel));

  }

}