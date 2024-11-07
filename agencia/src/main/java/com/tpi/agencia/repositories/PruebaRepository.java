
package com.tpi.agencia.repositories;

import com.tpi.agencia.models.Prueba;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PruebaRepository extends CrudRepository<Prueba, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Prueba p WHERE p.vehiculo.id = ?1 AND p.fechaHoraFin IS NULL")
    boolean existsByVehiculoIdAndFechaHoraFinIsNull(@Param("idVehiculo") Integer idVehiculo);

    List<Prueba> findByFechaHoraFinIsNull();


    @Query("SELECT p FROM Prueba p WHERE p.vehiculo.id = :vehiculoId " +
            "AND (p.fechaHoraFin IS NULL AND :fechaNotificacion BETWEEN p.fechaHoraInicio AND CURRENT_TIMESTAMP " +
            "OR :fechaNotificacion BETWEEN p.fechaHoraInicio AND p.fechaHoraFin)")
    Prueba findPruebaByVehiculoIdAndFechaNotificacion(
            @Param("vehiculoId") Integer vehiculoId,
            @Param("fechaNotificacion") LocalDateTime fechaNotificacion);

    @Query("SELECT p FROM Prueba p WHERE p.vehiculo.id = :vehiculoId " +
            "AND p.empleado.legajo = :idEmpleado " +
            "AND (p.fechaHoraFin IS NULL AND :fechaNotificacion BETWEEN p.fechaHoraInicio AND CURRENT_TIMESTAMP " +
            "OR :fechaNotificacion BETWEEN p.fechaHoraInicio AND p.fechaHoraFin)")
    Prueba findPruebaByVehiculoIdAndFechaNotificacionAndEmpleado(
            @Param("vehiculoId") Integer vehiculoId,
            @Param("fechaNotificacion") LocalDateTime fechaNotificacion,
            @Param("idEmpleado") Integer idEmpleado);

}
