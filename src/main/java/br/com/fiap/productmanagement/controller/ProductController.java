package br.com.fiap.productmanagement.controller;

import br.com.fiap.productmanagement.domain.entities.MessageEntity;
import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.domain.exception.UseCaseException;
import br.com.fiap.productmanagement.domain.usecase.SchedulingJobUseCase;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("products")
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
  public ResponseEntity<MessageEntity> processProduct(
          @RequestParam("file") MultipartFile file,
          @RequestParam(required = false) LocalDateTime date) throws UseCaseException {

    schedulingJobUseCase.start(file, date);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                    MessageEntity
                            .builder()
                              .title("Solicitação recebida")
                              .message("Processando produto")
                            .build());

  }

  @GetMapping
  public ResponseEntity<List<ProductEntity>> getAllProducts() {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.getProducts());

  }

  @GetMapping(value = "{product_id}")
  public ResponseEntity<ProductEntity> getProduct(@PathVariable("product_id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.getProduct(id));

  }

  @DeleteMapping(value = "{product_id}")
  public ResponseEntity<ProductEntity> removeProduct(@PathVariable("product_id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.removeProduct(id));

  }

  @PutMapping(value = "/{product_id}/stocks")
  public ResponseEntity<ProductEntity> updateProduct(
          @PathVariable("product_id") Long id,
          @RequestBody ProductEntity productEntity
  ) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.updateProduct(id, productEntity));

  }

}