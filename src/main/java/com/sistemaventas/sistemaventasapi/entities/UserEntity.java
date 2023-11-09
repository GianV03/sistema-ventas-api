package com.sistemaventas.sistemaventasapi.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_person")
public class UserEntity {

    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_person", referencedColumnName = "id_person")
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name="user_role", referencedColumnName = "id_role")
    private RolesEntity role;

    @Column(name="user_start_date")
    private Date startDate;
}
