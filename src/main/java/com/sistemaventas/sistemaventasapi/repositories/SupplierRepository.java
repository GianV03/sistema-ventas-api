package com.sistemaventas.sistemaventasapi.repositories;

import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, UUID> {
}
