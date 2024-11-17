package com.tpi.notificaciones.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Table(name = "NOTIFICACION_PROMOCION")
public class NotificacionPromocion extends Notificacion {

    // Atributos
    private String codigoPromocion;
    private LocalDate fechaExpiracion;


    // Constructor sin ID (para casos en los que no necesitas pasar el ID)
    public NotificacionPromocion(String codigoPromocion, LocalDate fechaExpiracion) {
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }

    // Constructor completo (incluyendo ID y atributos de la superclase)
    public NotificacionPromocion(Integer id, Date fechaNotificacion, String texto, String codigoPromocion, LocalDate fechaExpiracion) {
        super(id, fechaNotificacion, texto);
        this.codigoPromocion = codigoPromocion;
        this.fechaExpiracion = fechaExpiracion;
    }

}