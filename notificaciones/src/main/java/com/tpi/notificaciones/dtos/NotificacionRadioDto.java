package com.tpi.notificaciones.dtos;

import com.tpi.notificaciones.models.NotificacionRadio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificacionRadioDto extends NotificacionDto{
    private double latitud;
    private double longitud;
    private Integer idVehiculo;

    public NotificacionRadioDto(NotificacionRadio notificacionRadio){
        super(notificacionRadio.getId(), notificacionRadio.getTexto());
        this.latitud = notificacionRadio.getLatitud();
        this.longitud = notificacionRadio.getLongitud();
        this.idVehiculo = notificacionRadio.getIdVehiculo();
    }
}
