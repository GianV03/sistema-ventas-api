package com.sistemaventas.sistemaventasapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.UUID;

@Getter
@Setter
public class ProductTypeGetDTO {

    private UUID id;

    private String productTypeName;

    private String productTypeDetails;
}
