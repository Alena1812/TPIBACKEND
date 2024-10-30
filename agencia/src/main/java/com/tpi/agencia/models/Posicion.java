package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Posiciones")
@Data
public class Posicion {
    @Id
    private Integer id;

    @Column(name = "FECHA_HORA")
    private Date fechaHora;

    private Double latitud;
    private Double longitud;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

}
