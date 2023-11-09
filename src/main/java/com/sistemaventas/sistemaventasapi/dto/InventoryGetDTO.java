package com.sistemaventas.sistemaventasapi.dto;

import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
public class InventoryGetDTO {

    private UUID id;

    private SupplierEntity supplier;

    private ProductEntity product;

    private int stock;

    private int state;
}
