package com.sistemaventas.sistemaventasapi.repositories;

import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
 public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
  List<ProductEntity> findByNameStartingWithIgnoreCase(String name);

  List <ProductEntity> findBySupplierId(UUID id);

}
