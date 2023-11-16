package com.sistemaventas.sistemaventasapi.dto;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderGetDTO {
    private UUID id;

    private UUID supplierId;

    private Date orderDate;

    private Date orderDeliveryDate;

    private Date orderRealDeliveryDate;

    private int state;

    private double subTotal;

    private double total;

}
