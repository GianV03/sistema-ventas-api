package com.sistemaventas.sistemaventasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class RolesEntity {
    @Id
    @Column(name="id_role")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="role_name")
    private String name;

    @Column(name="role_description")
    private String description;

}
