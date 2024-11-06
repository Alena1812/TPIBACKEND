package com.tpi.agencia.dtos.expertos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)

public class NotificacionZonaPeligrosaDto extends NotificacionDto{
    private double latActual;
    private double lonActual;
    private String nivelPeligro;
    private Integer idVehiculo;
}
