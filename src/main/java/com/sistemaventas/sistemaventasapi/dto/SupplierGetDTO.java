package com.sistemaventas.sistemaventasapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class SupplierGetDTO {
    private UUID id;

    private String name;

    private String documentType;

    private String document;

    private String address;

    private String details;

    private String userCreate;

    private LocalDateTime dateCreate;
}
