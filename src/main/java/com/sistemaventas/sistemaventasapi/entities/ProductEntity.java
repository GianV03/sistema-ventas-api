package com.sistemaventas.sistemaventasapi.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ProductEntity {
    @Id
    @Column(name="id_product")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="product_name")
    private String name;

    @Column(name="product_type")
    private ProductTypeEntity type;

    @Column(name="product_sale_price")
    private Double salePrice;

    @Column(name="product_purchase_price")
    private Double purchasePrice;

    @Column(name="product_supplier")
    private SupplierEntity productSupplier;


    @Column(name="product_details")
    private String details;

 /*   @Column(name="product_user_creation")
    private User userCreation;

    @Column(name="product_user_update")
    private User userUpdate;*/

    @Column(name="product_date_creation")
    private Date creationDate;

    @Column(name="product_date_update")
    private Date updateDate;

}
