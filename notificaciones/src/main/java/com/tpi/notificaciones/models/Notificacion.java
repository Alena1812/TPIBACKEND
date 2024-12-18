package com.tpi.notificaciones.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Notificacion {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // Indica que el campo 'fechaNotificacion' debe ser tratado como una fecha y hora, almacenada en el formato TIMESTAMP en la base de datos.
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    private String texto;

    // Constructor
    public Notificacion(Integer id, Date fechaNotificacion, String texto) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.texto = texto;
    }
}
