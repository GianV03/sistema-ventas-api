package com.sistemaventas.sistemaventasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="supplier")
public class SupplierEntity {

    @Id
    @Column(name="id_supplier")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="supplier_name")
    private String name;

    @Column(name="supplier_document_type")
    private String documentType;

    @Column(name="supplier_document")
    private String document;

    @Column(name="supplier_address")
    private String address;

    @Column(name="supplier_details")
    private String details;

    @Column(name="supplier_user_create")
    private String userCreate;

    @Column(name="supplier_date_create")
    private LocalDateTime dateCreate;

}
