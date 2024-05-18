package br.com.fiap.productmanagement.controller;

import br.com.fiap.productmanagement.domain.entities.MessageEntity;
import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.domain.exception.UseCaseException;
import br.com.fiap.productmanagement.domain.usecase.SchedulingJobUseCase;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiap.productmanagement.utils.MessageEnumUtils.PROCESS_PRODUCT;
import static br.com.fiap.productmanagement.utils.MessageEnumUtils.TITLE_PROCESS_PRODUCT;

@RestController
@RequestMapping("products")
@Tag(name = "Product Controller", description = "The customer can schedule (or immediately carry out) the processing of their product control from a .csv file and control each product through the API resources")
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

  @Operation(
          summary = "Process product control by .csv file",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
          )
  )
  @ApiResponse(
          responseCode = "200",
          description = "Returns a message signaling that the file was received successfully"
  )
  @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
  public ResponseEntity<MessageEntity> processProduct(
          @RequestParam("file") MultipartFile file,
          @RequestParam(required = false) LocalDateTime date) throws UseCaseException {

    schedulingJobUseCase.start(file, date);

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                    MessageEntity
                            .builder()
                              .title(TITLE_PROCESS_PRODUCT)
                              .message(PROCESS_PRODUCT.getMessage())
                            .build());

  }

  @Operation(summary = "List all products")
  @ApiResponse(responseCode = "200", description = "Gets list of all products")
  @GetMapping
  public ResponseEntity<List<ProductEntity>> getAllProducts() {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.getProducts());

  }

  @Operation(summary = "Returns a product by id")
  @ApiResponse(responseCode = "200", description = "Gets a specific product")
  @GetMapping(value = "{product_id}")
  public ResponseEntity<ProductEntity> getProduct(@PathVariable("product_id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.getProduct(id));

  }

  @Operation(summary = "Remove a product by id")
  @ApiResponse(responseCode = "200", description = "Remove a specific product")
  @DeleteMapping(value = "{product_id}")
  public ResponseEntity<ProductEntity> removeProduct(@PathVariable("product_id") Long id) {

    return  ResponseEntity
            .status(HttpStatus.OK)
            .body(productManagementOutputPort.removeProduct(id));

  }

  @Operation(summary = "Update a product by id")
  @ApiResponse(responseCode = "200", description = "Update a specific product")
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