package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Vehiculos")
@Data
public class Vehiculo {
    @Id
    private Integer id;
    private String patente;
    private Integer anio;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo")
    private Set<Prueba> pruebas = new HashSet<>();

    @OneToMany(mappedBy = "vehiculo")
    private Set<Posicion> posicions = new HashSet<>();

}