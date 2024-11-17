package com.tpi.notificaciones.models;
import com.tpi.notificaciones.dtos.PosicionDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    public NotificacionRadio(PosicionDto posicion, Integer idVehiculo) {
        this.latitud = posicion.getCoordenadas().getLatitud();
        this.longitud = posicion.getCoordenadas().getLongitud();
        this.idVehiculo = idVehiculo;
    }

    public NotificacionRadio(Integer id, Date fechaNotificacion, String texto, double latitud, double longitud, Integer idVehiculo) {
        super(id, fechaNotificacion, texto);
        this.latitud = latitud;
        this.longitud = longitud;
        this.idVehiculo = idVehiculo;
    }
}