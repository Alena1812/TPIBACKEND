
package com.tpi.agencia.repositories;

import com.tpi.agencia.models.Prueba;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PruebaRepository extends CrudRepository<Prueba, Integer> {
    boolean existsByVehiculoIdAndFechaHoraFinIsNull(Integer idVehiculo);
    List<Prueba> findByFechaHoraFinIsNull();
}
