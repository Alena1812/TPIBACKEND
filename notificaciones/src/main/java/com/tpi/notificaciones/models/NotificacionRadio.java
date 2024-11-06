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
@Table(name = "NOTIFICACION_RADIO")
public class NotificacionRadio extends Notificacion {

    //Atributos
    private double latitud;
    private double longitud;
    private Integer idVehiculo;

    //Constructor
    public NotificacionRadio(double latitud, double longitud, Integer idVehiculo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.idVehiculo = idVehiculo;
    }

    public NotificacionRadio(Integer id, LocalDateTime fechaNotificacion, String texto, double latitud, double longitud, Integer idVehiculo) {
        super(id, fechaNotificacion, texto);
        this.latitud = latitud;
        this.longitud = longitud;
        this.idVehiculo = idVehiculo;
    }
}