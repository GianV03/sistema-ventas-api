package com.sistemaventas.sistemaventasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import javax.persistence.*;
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
