package com.tpi.agencia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Marcas")
@Data
public class Marca {
    @Id
    private Integer id;
    private String nombre;

    @OneToMany(mappedBy = "marca")
    private Set<Modelo> modelos = new HashSet<>();

}
