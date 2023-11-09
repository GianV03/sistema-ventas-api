package com.sistemaventas.sistemaventasapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ProductUpdatePostDTO {

    private UUID id;

    private String name;

    private UUID typeId;

    private Double salePrice;

    private Double purchasePrice;

    private UUID supplierId;

    private String details;

    //private UUID userCreationId;

    private Date creationDate;

}
