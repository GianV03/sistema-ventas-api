package com.sistemaventas.sistemaventasapi.repositories;

import com.sistemaventas.sistemaventasapi.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
}
