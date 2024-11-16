package com.tpi.notificaciones.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class NotificacionDto {
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaNotificacion;
    private String texto;

    public NotificacionDto(Integer id, String texto) {
        this.id = id;
        this.fechaNotificacion = LocalDateTime.now();
        this.texto = texto;
    }
}