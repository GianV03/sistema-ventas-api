package com.sistemaventas.sistemaventasapi.repositories;

import com.sistemaventas.sistemaventasapi.entities.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, UUID> {
}
