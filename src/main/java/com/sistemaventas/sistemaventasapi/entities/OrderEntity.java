package com.sistemaventas.sistemaventasapi.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class OrderEntity {

    @Id
    @Column(name="id_order")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="supplier", referencedColumnName = "id_supplier")
    private SupplierEntity supplier;

    /*
    @ManyToOne
    @JoinColumn(name="product", referencedColumnName = "id_product")
    private ProductEntity product;
     */

    @Column(name="order_date")
    private Date orderDate;

    @Column(name="order_delivery_date")
    private Date orderDeliveryDate;

    @Column(name="order_real_delivery_date")
    private Date orderRealDeliveryDate;

    @Column(name="order_state")
    private int state;

    @Column(name="order_sub_total")
    private double subTotal;

    @Column(name="order_total")
    private double total;
}
