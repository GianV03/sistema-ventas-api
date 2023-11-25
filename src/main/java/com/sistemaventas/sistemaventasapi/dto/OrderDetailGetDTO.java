package com.sistemaventas.sistemaventasapi.dto;

import com.sistemaventas.sistemaventasapi.entities.OrderEntity;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
public class OrderDetailGetDTO {
    private UUID orderId;

    private UUID productId;

    private int quantity;
    private double price;

    private double total;
}
