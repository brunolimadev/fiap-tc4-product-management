package br.com.fiap.productmanagement.ports.outputport;

import br.com.fiap.productmanagement.domain.entities.ProductEntity;
import br.com.fiap.productmanagement.ports.exception.OutputPortException;

import java.util.List;

public interface ProductManagementOutputPort {

  List<ProductEntity> getProducts() throws OutputPortException;

  ProductEntity getProduct(Long id) throws OutputPortException;

  ProductEntity removeProduct(Long id) throws OutputPortException;

  ProductEntity updateProduct(Long id, ProductEntity productEntity) throws OutputPortException;

}