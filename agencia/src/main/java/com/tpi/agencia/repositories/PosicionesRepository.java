package com.tpi.agencia.repositories;

import com.tpi.agencia.models.Posicion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PosicionesRepository extends CrudRepository<Posicion, Integer> {
    @Query("SELECT p FROM Posicion p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHora BETWEEN :inicio AND :fin")
    List<Posicion> findByIdVehiculoAndFechaHoraBetween(@Param("idVehiculo") Integer idVehiculo,
                                                             @Param("inicio") Date inicio,
                                                             @Param("fin") Date fin);

}