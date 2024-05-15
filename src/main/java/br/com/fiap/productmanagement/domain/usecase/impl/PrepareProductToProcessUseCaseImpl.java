package br.com.fiap.productmanagement.domain.usecase.impl;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.domain.usecase.PrepareProductToProcessUseCase;

import java.time.LocalDateTime;

public class PrepareProductToProcessUseCaseImpl implements PrepareProductToProcessUseCase {

  @Override
  public ProductEntity process(ProductEntity item) throws Exception {

    item.setPrice(item.getPrice().replace(",", "."));
    item.setCreateDateTime(LocalDateTime.now());

    return item;

  }

}