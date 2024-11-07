package com.tpi.agencia.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PosicionDto {
    private int id;
    private VehiculoDto vehiculo;
    private Coordenadas coordenadas;
    private String mensaje;

    @Data
    public static class Coordenadas {
        private double lat;
        private double lon;
    }

}
