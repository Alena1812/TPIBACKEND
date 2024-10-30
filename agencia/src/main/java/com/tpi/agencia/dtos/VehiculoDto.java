package com.tpi.agencia.dtos;

import lombok.Data;

@Data
public class VehiculoDto {
    private Integer id;
    private String patente;
    private Integer anio;
    private Integer idModelo;

}
