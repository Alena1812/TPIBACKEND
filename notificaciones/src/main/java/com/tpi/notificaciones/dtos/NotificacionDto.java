package com.tpi.notificaciones.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class NotificacionDto {
    private Integer id;
    private List<String> emails;
    private LocalDateTime fechaNotificacion;
    private String texto;

    public NotificacionDto(Integer id, LocalDateTime fechaNotificacion, String texto) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.texto = texto;
    }
}