package com.sistemaventas.sistemaventasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order_detail")
@IdClass(OrderDetailId.class)
public class OrderDetailEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id_order")
    private OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product")
    private ProductEntity product;

    @Column(name="order_detail_quantity")
    private int quantity;

    @Column(name="oder_detail_product_price")
    private double price;

    @Column(name="order_detail_total")
    private double total;
}
