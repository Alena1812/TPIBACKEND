package com.tpi.notificaciones.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class NotificacionZona extends Notificacion{
    private String zona;
    private String nivelPeligro;
    private Integer idPrueba;
}
