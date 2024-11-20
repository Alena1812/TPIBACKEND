package com.tpi.notificaciones.dtos;

import com.tpi.notificaciones.models.NotificacionRadio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotificacionRadioDto extends NotificacionDto{
    private Double latitud;
    private Double longitud;
    private Integer idVehiculo;

    public NotificacionRadioDto(NotificacionRadio notificacionRadio){
        super(notificacionRadio.getId(), notificacionRadio.getFechaNotificacion(), notificacionRadio.getTexto());
        this.latitud = notificacionRadio.getLatitud();
        this.longitud = notificacionRadio.getLongitud();
        this.idVehiculo = notificacionRadio.getIdVehiculo();
    }
}
