package com.sistemaventas.sistemaventasapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_type")
public class ProductTypeEntity {
    @Id
    @Column(name="id_product_type")
    private UUID id;

    @Column(name="product_type_name")
    private String productTypeName;

    @Column(name="product_type_details")
    private String productTypeDetails;
}
