package com.tpi.notificaciones.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tpi.notificaciones.service.PosicionDtoDataDeserializer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PosicionDto {
    private Integer id;
    private VehiculoDto vehiculoDto;
    @Getter
    private Coordenadas coordenadas;
    private String texto;

    @JsonCreator
    public PosicionDto(
            @JsonProperty("id") Integer id,
            @JsonProperty("vehiculoDto") VehiculoDto vehiculoDto,
            @JsonProperty("coordenadas") Coordenadas coordenadas,
            @JsonProperty("texto") String texto) {
                this.id = id;
                this.vehiculoDto= vehiculoDto;
                this.coordenadas = coordenadas;
                this.texto = texto;
    }

    @Data
    @NoArgsConstructor
    public static class Coordenadas {
        private double latitud;
        private double longitud;

        @JsonCreator
        public Coordenadas(
                @JsonProperty("latitud") double latitud,
                @JsonProperty("longitud") double longitud) {
            this.latitud = latitud;
            this.longitud = longitud;
        }
    }
}

