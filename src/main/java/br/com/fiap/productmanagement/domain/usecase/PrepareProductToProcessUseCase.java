package br.com.fiap.productmanagement.domain.usecase;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import org.springframework.batch.item.ItemProcessor;

public interface PrepareProductToProcessUseCase extends ItemProcessor<ProductEntity, ProductEntity> {}