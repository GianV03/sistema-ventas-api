package com.sistemaventas.sistemaventasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private SupplierEntity supplier;

    @ManyToOne
    @JoinColumn(name="id_product")
    private ProductEntity product;

    @Column(name="product_stock")
    private int stock;

    @Column(name="product_state")
    private int state;
}
