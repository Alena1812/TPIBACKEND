package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Pruebas")
@Data
public class Prueba {
    @Id
    private Integer id;

    @Column(name = "FECHA_HORA_INICIO")
    private Date fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private Date fechaHoraFin;

    private String comentarios;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    private Interesado interesado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    private Empleado empleado;

}
