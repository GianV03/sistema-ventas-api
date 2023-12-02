package com.sistemaventas.sistemaventasapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderDetailPostDTO {
    private UUID orderId;
    private UUID productId;
    private int quantity;
    private double price;
    private double total;
}
