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
@Table(name="product")
public class ProductEntity {

    @Id
    @Column(name="id_product")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name="product_type", referencedColumnName = "id_product_type")
    private ProductTypeEntity type;

    @Column(name="product_sale_price")
    private Double salePrice;

    @Column(name="product_purchase_price")
    private Double purchasePrice;

    @ManyToOne
    @JoinColumn(name="product_supplier", referencedColumnName = "id_supplier")
    private SupplierEntity supplier;

    @Column(name="product_details")
    private String details;

    @ManyToOne
    @JoinColumn(name="product_user_creation", referencedColumnName = "id_user")
    private UserEntity userCreation;

    @ManyToOne
    @JoinColumn(name="product_user_update", referencedColumnName = "id_user")
    private UserEntity userUpdate;

    @Column(name="product_date_creation")
    private Date creationDate;

    @Column(name="product_date_update")
    private Date updateDate;

}
