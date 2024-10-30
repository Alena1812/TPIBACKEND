package com.tpi.agencia.repositories;

import com.tpi.agencia.models.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {
}
