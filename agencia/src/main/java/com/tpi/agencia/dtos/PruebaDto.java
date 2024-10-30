package com.tpi.agencia.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PruebaDto {
    private Integer id;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String comentarios;
    private Integer idVehiculo;
    private Integer idInteresado;
    private Integer idEmpleado;


}
