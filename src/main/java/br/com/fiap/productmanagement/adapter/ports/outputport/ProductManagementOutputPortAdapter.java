package br.com.fiap.productmanagement.adapter.ports.outputport;

import br.com.fiap.productmanagement.adapter.repositories.ProductRepository;
import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;

import java.util.List;

public class ProductManagementOutputPortAdapter implements ProductManagementOutputPort {

  private final ProductRepository productRepository;

  public ProductManagementOutputPortAdapter(ProductRepository productRepository) {

    this.productRepository = productRepository;

  }

  @Override
  public List<ProductModel> getProducts() {

    return productRepository.findAll();

  }

}