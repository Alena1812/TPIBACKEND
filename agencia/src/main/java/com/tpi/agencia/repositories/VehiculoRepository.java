package com.tpi.agencia.repositories;

import com.tpi.agencia.models.Vehiculo;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {
}