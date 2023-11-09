package com.sistemaventas.sistemaventasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="person")
public class PersonEntity {

    @Id
    @Column(name="id_person")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="person_name")
    private String name;

    @Column(name="person_last_name")
    private String lastName;

    @Column(name="person_sex")
    private String sex;

    @Column(name="person_document_type")
    private Integer documentType;

    @Column(name="person_document")
    private String document;

    @Column(name="person_birthday")
    private Date birthDate;

    @Column(name="person_phone")
    private String phone;

    @Column(name="person_email")
    private String email;

    @Column(name="person_address")
    private String address;
}
