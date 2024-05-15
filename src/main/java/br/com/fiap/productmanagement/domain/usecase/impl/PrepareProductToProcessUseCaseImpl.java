package br.com.fiap.productmanagement.domain.usecase.impl;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.domain.exception.UseCaseException;
import br.com.fiap.productmanagement.domain.usecase.PrepareProductToProcessUseCase;
import lombok.NonNull;

import java.time.LocalDateTime;

public class PrepareProductToProcessUseCaseImpl implements PrepareProductToProcessUseCase {

  @Override
  public ProductEntity process(@NonNull ProductEntity productEntity) throws UseCaseException {

    try {

      productEntity.setPrice(productEntity.getPrice().replace(",", "."));
      productEntity.setCreateDateTime(LocalDateTime.now());

      return productEntity;

    } catch (Exception exception) {

      throw new UseCaseException("Ocorreu um erro ao tentar preparar produto para processamento");

    }

  }

}