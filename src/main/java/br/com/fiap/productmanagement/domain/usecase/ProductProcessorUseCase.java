package br.com.fiap.productmanagement.domain.usecase;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;

public class ProductProcessorUseCase implements ItemProcessor<ProductEntity, ProductEntity> {

  @Override
  public ProductEntity process(ProductEntity item) throws Exception {

    item.setPrice(item.getPrice().replace(",", "."));
    item.setCreateDateTime(LocalDateTime.now());

    return item;

  }

}