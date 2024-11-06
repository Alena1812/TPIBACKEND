package com.tpi.notificaciones.repositories;

import com.tpi.notificaciones.models.Notificacion;
import org.springframework.data.repository.CrudRepository;

public interface NotificacionRepository extends CrudRepository<Notificacion, Integer> {
}
