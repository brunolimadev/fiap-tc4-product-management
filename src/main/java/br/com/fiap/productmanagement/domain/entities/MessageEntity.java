package br.com.fiap.productmanagement.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MessageEntity {

  private String title;
  private String message;

}
