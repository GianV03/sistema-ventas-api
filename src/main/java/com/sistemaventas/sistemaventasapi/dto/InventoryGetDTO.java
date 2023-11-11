package com.sistemaventas.sistemaventasapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InventoryGetDTO {

    private UUID id;

    private String supplierName;

    private String productName;

    private int stock;

    private int state;
}
