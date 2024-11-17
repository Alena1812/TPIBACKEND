package com.tpi.notificaciones.dtos;

import com.tpi.notificaciones.models.NotificacionPromocion;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionPromocionDto extends NotificacionDto{
    private String codigoPromocion;
    private LocalDate fechaExpiracion;

    public NotificacionPromocionDto() {
        super(); // Esto llamará al constructor sin argumentos de NotificacionDto
    }

    public NotificacionPromocionDto(NotificacionPromocion notificacion) {
        super(notificacion.getId(), notificacion.getFechaNotificacion(), notificacion.getTexto());
        this.codigoPromocion = notificacion.getCodigoPromocion();
        this.fechaExpiracion = notificacion.getFechaExpiracion();
    }
}
