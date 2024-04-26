package br.com.fiap.productmanagement;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;

public class ProductProcessor implements ItemProcessor<Product, Product> {

  @Override
  public Product process(Product item) throws Exception {

    item.setPrice(item.getPrice().replace(",", "."));
    item.setCreateDateTime(LocalDateTime.now());

    return item;

  }

}