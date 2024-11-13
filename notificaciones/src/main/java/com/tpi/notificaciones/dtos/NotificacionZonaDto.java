package com.tpi.notificaciones.dtos;

import com.tpi.notificaciones.models.NotificacionZona;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificacionZonaDto extends NotificacionDto {
    private double latitud;
    private double longitud;
    private String nivelPeligro;
    private Integer idVehiculo;

    public NotificacionZonaDto(NotificacionZona notificacionZona){
        super(notificacionZona.getId(), notificacionZona.getTexto());
        this.latitud = notificacionZona.getLatitud();
        this.longitud = notificacionZona.getLongitud();
        this.nivelPeligro = notificacionZona.getNivelPeligro();
        this.idVehiculo = notificacionZona.getIdVehiculo();
    }
}

