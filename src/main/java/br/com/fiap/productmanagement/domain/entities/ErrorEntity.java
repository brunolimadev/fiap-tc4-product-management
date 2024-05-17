package br.com.fiap.productmanagement.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorEntity {

  private String title;
  private String message;

}
