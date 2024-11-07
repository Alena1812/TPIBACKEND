package com.tpi.notificaciones.repositories;

import com.tpi.notificaciones.models.NotificacionPromocion;
import com.tpi.notificaciones.models.NotificacionZona;
import org.springframework.data.repository.CrudRepository;

public interface NotificacionZonaRepository extends CrudRepository<NotificacionZona, Integer> {
}
