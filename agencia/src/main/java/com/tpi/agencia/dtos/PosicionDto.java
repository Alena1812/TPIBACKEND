package com.tpi.agencia.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PosicionDto {
    private int id;
    private VehiculoDto vehiculo;
    private Date fechaHora;
    private Double latitud;
    private Double longitud;
    private String mensaje;

}
