package com.tpi.agencia.dtos.externos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class NotificacionDto {
    private Integer id;
    private Date fechaNotificacion;
    private String texto;

    public NotificacionDto(Integer id, Date fechaNotificacion, String texto) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.texto = texto;
    }
}
