package com.sistemaventas.sistemaventasapi.dto;

import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InventoryUpdateDTO {

    private UUID id;

    private SupplierEntity supplier;

    private ProductEntity product;

    private int stock;

}
