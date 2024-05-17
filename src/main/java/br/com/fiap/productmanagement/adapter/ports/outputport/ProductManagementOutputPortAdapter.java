package br.com.fiap.productmanagement.adapter.ports.outputport;

import br.com.fiap.productmanagement.adapter.repositories.ProductRepository;
import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.ports.exception.OutputPortException;
import br.com.fiap.productmanagement.ports.outputport.ProductManagementOutputPort;
import br.com.fiap.productmanagement.utils.ConvertJpaModelToDomainEntityUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ProductManagementOutputPortAdapter implements ProductManagementOutputPort {

  private final ProductRepository productRepository;

  public ProductManagementOutputPortAdapter(ProductRepository productRepository) {

    this.productRepository = productRepository;

  }

  @Override
  public List<ProductEntity> getProducts() throws OutputPortException {

    try {

      return productRepository.findAll()
              .stream()
              .map(ConvertJpaModelToDomainEntityUtils::convert)
              .toList();

    } catch (Exception exception) {

      throw new OutputPortException("Ocorreu um erro ao tentar recuperar os produtos");

    }

  }

  @Override
  public ProductEntity getProduct(Long id) throws OutputPortException {

    try {

      return ConvertJpaModelToDomainEntityUtils.convert(productRepository.findById(id).orElseThrow());

    } catch (Exception exception) {

      throw new OutputPortException("Ocorreu um erro ao tentar recuperar o produto");

    }

  }

  @Override
  public ProductEntity removeProduct(Long id) throws OutputPortException {

    try {

      var product = productRepository.findById(id).orElseThrow();

      productRepository.deleteById(product.getId());

      return ConvertJpaModelToDomainEntityUtils.convert(product);

    } catch (Exception exception) {

      throw new OutputPortException("Ocorreu um erro ao tentar remover o produto");

    }

  }

  @Override
  public ProductEntity updateProduct(Long id, ProductEntity productEntity) throws OutputPortException {

    try {

      var product = productRepository.findById(id).orElseThrow();

      product.setDescription(productEntity.getDescription());
      product.setPrice(productEntity.getPrice());
      product.setStoreQuantity(productEntity.getStoreQuantity());
      product.setUpdateDateTime(LocalDateTime.now());

      return ConvertJpaModelToDomainEntityUtils.convert(productRepository.save(product));

    } catch (Exception exception) {

      throw new OutputPortException("Ocorreu um erro ao tentar atualizar o produto");

    }

  }

}