package br.com.fiap.productmanagement.adapter.ports.outputport;

import br.com.fiap.productmanagement.adapter.repositories.ProductRepository;
import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;

import java.time.LocalDateTime;
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

  @Override
  public ProductModel getProduct(Long id) {

    return productRepository.findById(id).orElseThrow();

  }

  @Override
  public ProductModel removeProduct(Long id) {

    var product = productRepository.findById(id).orElseThrow();

    productRepository.deleteById(product.getId());

    return product;

  }

  @Override
  public ProductModel updateProduct(Long id, ProductModel productModel) {

    var product = productRepository.findById(id).orElseThrow();

    product.setDescription(productModel.getDescription());
    product.setPrice(productModel.getPrice());
    product.setStoreQuantity(productModel.getStoreQuantity());
    product.setUpdateDateTime(LocalDateTime.now());

    return productRepository.save(product);

  }

}