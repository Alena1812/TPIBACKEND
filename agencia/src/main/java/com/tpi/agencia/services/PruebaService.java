package com.tpi.agencia.services;
import com.tpi.agencia.repositories.*;
import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PruebaService {
    private final PruebaRepository repository;
    private final EmpleadoRepository empleadoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final InteresadoRepository interesadoRepository;

    @Autowired
    public PruebaService(PruebaRepository repository, EmpleadoRepository empleadoRepository, VehiculoRepository vehiculoRepository, InteresadoRepository interesadoRepository) {
        this.repository = repository;
        this.empleadoRepository = empleadoRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.interesadoRepository = interesadoRepository;
    }

    @Transactional
    public Prueba create(PruebaDto prueba) {
        Prueba nuevaPrueba = buildPruebaFromDto(prueba);
        return repository.save(nuevaPrueba);
    }

    public Prueba findById(Integer id) throws ServiceException {
        return repository.findById(id).orElseThrow(() -> new ServiceException("Prueba no encontrada"));
    }

    public Iterable<Prueba> findAll() {
        return repository.findAll();
    }

    public List<Prueba> getPruebasEnCurso() {
        return repository.findByFechaHoraFinIsNull();
    }

    public Prueba finalizarPrueba(Integer id, String comentarios) {
        Prueba prueba = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        if (prueba.getFechaHoraFin() != null) {
            throw new IllegalArgumentException("La prueba ya ha sido finalizada.");
        }

        prueba.setFechaHoraFin(new Date());
        prueba.setComentarios(comentarios);

        return repository.save(prueba);
    }

    private Prueba buildPruebaFromDto(PruebaDto pruebaDto) {
        Vehiculo vehiculo = vehiculoRepository.findById(pruebaDto.getIdVehiculo())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
        if (repository.existsByVehiculoIdAndFechaHoraFinIsNull(vehiculo.getId())) {
            throw new IllegalArgumentException("El vehículo está siendo probado.");
        }

        Empleado empleado = empleadoRepository.findById(pruebaDto.getIdEmpleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Interesado interesado = interesadoRepository.findById(pruebaDto.getIdInteresado())
                .orElseThrow(() -> new IllegalArgumentException("Interesado no encontrado"));
        if (interesado.getFechaVtoLicencia().before(new Date())) {
            throw new IllegalArgumentException("La licencia del interesado está vencida.");
        }
        if (interesado.getRestringido()) {
            throw new IllegalArgumentException("El interesado está restringido para probar vehículos.");
        }

        Prueba prueba = new Prueba();
        prueba.setVehiculo(vehiculo);
        prueba.setEmpleado(empleado);
        prueba.setInteresado(interesado);
        prueba.setFechaHoraInicio(new Date());

        return prueba;
    }

    public void deletePrueba(Integer id) {
        Prueba existingPrueba = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));
        repository.delete(existingPrueba);
    }
}


