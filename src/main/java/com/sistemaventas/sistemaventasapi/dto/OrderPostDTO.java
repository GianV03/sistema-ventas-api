package com.sistemaventas.sistemaventasapi.dto;

import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderPostDTO {

    private UUID supplier;

    private Date orderDate;

    private Date orderDeliveryDate;

    private Date orderRealDeliveryDate;

    private int state;

    private double subTotal;

    private double total;
}
