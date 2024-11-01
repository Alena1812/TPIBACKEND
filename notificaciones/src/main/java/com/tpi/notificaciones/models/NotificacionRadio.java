package com.tpi.notificaciones.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "NOTIFICACION_RADIO_EXCEDIDO")
public class NotificacionRadio extends Notificacion {

    //Atributos
    private double radioMaximo;
    private String ubicacionActual;
    private Integer idPrueba;

    //Constructor
    public NotificacionRadio(double radioMaximo, String ubicacionActual, Integer idPrueba) {
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
        this.idPrueba = idPrueba;
    }

    public NotificacionRadio(Integer id, LocalDateTime fechaNotificacion, String mensaje, double radioMaximo, String ubicacionActual, Integer idPrueba) {
        super(id, fechaNotificacion, mensaje);
        this.radioMaximo = radioMaximo;
        this.ubicacionActual = ubicacionActual;
        this.idPrueba = idPrueba;
    }
}