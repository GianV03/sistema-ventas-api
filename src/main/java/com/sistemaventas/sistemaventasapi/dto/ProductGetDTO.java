package com.sistemaventas.sistemaventasapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistemaventas.sistemaventasapi.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ProductGetDTO {
    private UUID id;

    private String name;

    private UUID typeId;

    private String typeName;

    private Double salePrice;

    private Double purchasePrice;

    private UUID supplierId;

    private String details;

    private UUID userCreationId;

    private UUID userUpdateId;

    private Date creationDate;

    private Date updateDate;
}
