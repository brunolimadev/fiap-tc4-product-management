package br.com.fiap.productmanagement.ports.outputport;

import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;

import java.util.List;

public interface ProductManagementOutputPort {

  List<ProductModel> getProducts();

  ProductModel getProduct(Long id);

  ProductModel removeProduct(Long id);

  ProductModel updateProduct(Long id, ProductModel productModel);

}