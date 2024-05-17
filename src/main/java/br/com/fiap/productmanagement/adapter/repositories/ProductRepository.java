package br.com.fiap.productmanagement.adapter.repositories;

import br.com.fiap.productmanagement.adapter.repositories.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> { }