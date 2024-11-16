package com.tpi.agencia.dtos.externos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class NotificacionDto {
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaNotificacion;
    private String texto;

    public NotificacionDto(Integer id, LocalDateTime fechaNotificacion, String texto) {
        this.id = id;
        this.fechaNotificacion = fechaNotificacion;
        this.texto = texto;
    }
}
