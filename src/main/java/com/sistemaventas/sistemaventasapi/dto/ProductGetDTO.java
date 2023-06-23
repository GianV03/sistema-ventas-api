package com.sistemaventas.sistemaventasapi.dto;

import com.sistemaventas.sistemaventasapi.entities.ProductTypeEntity;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.UUID;

public class ProductGetDTO {
    private UUID id;

    private String name;

    private UUID type;

    private Double salePrice;

    private Double purchasePrice;

    private UUID productSupplier;

    private String details;

 /*   @Column(name="product_user_creation")
    private User userCreation;

    @Column(name="product_user_update")
    private User userUpdate;*/

    private Date creationDate;

    private Date updateDate;
}
