package com.sistemaventas.sistemaventasapi.repositories;

import com.sistemaventas.sistemaventasapi.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, UUID> {
}
