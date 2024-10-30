package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Modelos")
@Data
public class Modelo {
    @Id
    private Integer id;
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MARCA")
    private Marca marca;

    @OneToMany(mappedBy = "modelo")
    private Set<Vehiculo> vehiculos = new HashSet<>();

}
