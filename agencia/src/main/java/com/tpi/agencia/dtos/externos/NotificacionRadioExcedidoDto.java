package com.tpi.agencia.dtos.externos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificacionRadioExcedidoDto extends NotificacionDto{
    private double latitud;
    private double longitud;
    private Integer idVehiculo;

    public NotificacionRadioExcedidoDto(Integer id, Date fechaNotificacion, String texto, Double latitud, Double longitud, Integer idVehiculo) {
        super(id, fechaNotificacion, texto);
        this.latitud = latitud;
        this.longitud = longitud;
        this.idVehiculo = idVehiculo;
    }
    @Override
    public String toString() {
        return "NotificacionRadioExcedidoDto{" +
                "id=" + getId() +
                ", fechaNotificacion=" + getFechaNotificacion() +
                ", texto='" + getTexto() + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", idVehiculo=" + idVehiculo +
                '}';
    }

}
