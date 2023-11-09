package com.sistemaventas.sistemaventasapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SupplierUpdateDTO {

    private UUID id;

    private String name;

    private String documentType;

    private String document;

    private String address;

    private String details;

    private String userCreate;

    private LocalDateTime dateCreate;
}
