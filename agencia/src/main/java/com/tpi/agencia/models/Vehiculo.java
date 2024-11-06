package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
@Entity
@Table(name = "Vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patente;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    private Modelo modelo;

    private Integer anio;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    private Set<Prueba> pruebas;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    private Set<Posicion> posiciones;

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
        if (modelo != null) {
            modelo.getVehiculos().add(this);
        }
    }
}