package com.sistemaventas.sistemaventasapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderTrayDTO {
    private UUID id;

    private String supplierName;

    private Date orderDate;

    private Date orderDeliveryDate;

    private Date orderRealDeliveryDate;

    private int state;

    private double subTotal;

    private double total;
}
