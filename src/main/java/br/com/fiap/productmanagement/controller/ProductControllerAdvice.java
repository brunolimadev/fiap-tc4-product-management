package br.com.fiap.productmanagement.controller;

import br.com.fiap.productmanagement.domain.entities.MessageEntity;
import br.com.fiap.productmanagement.domain.exception.EntityException;
import br.com.fiap.productmanagement.domain.exception.UseCaseException;
import br.com.fiap.productmanagement.ports.exception.InputPortException;
import br.com.fiap.productmanagement.ports.exception.OutputPortException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static br.com.fiap.productmanagement.utils.MessageEnumUtils.*;

@ControllerAdvice
public class ProductControllerAdvice {

  @ExceptionHandler(
          {
                  EntityException.class,
                  UseCaseException.class,
          }
  )
  public ResponseEntity<MessageEntity> handleBadRequest(RuntimeException exception) {

    return ResponseEntity
            .badRequest()
            .body(
                    MessageEntity
                            .builder()
                              .title(TIME_DOMAIN_EXCEPTION)
                              .message(exception.getMessage())
                            .build()
            );

  }

  @ExceptionHandler(
          {
                  InputPortException.class,
                  OutputPortException.class
          }
  )
  public ResponseEntity<MessageEntity> handleUnProcessableEntity(RuntimeException exception) {

    return ResponseEntity
            .unprocessableEntity()
            .body(
                    MessageEntity
                            .builder()
                            .title(TITLE_PORTS_EXCEPTION)
                            .message(exception.getMessage())
                            .build()
            );

  }

}
