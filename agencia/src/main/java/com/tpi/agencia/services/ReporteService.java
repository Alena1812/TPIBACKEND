package com.tpi.agencia.services;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.dtos.report.response.DistanciaVehiculoResponse;
import com.tpi.agencia.models.Posicion;
import com.tpi.agencia.models.Prueba;
import com.tpi.agencia.models.Vehiculo;
import com.tpi.agencia.repositories.PosicionesRepository;
import com.tpi.agencia.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteService {
    private final PruebaRepository pruebaRepository;
    private final PosicionesRepository posicionesRepository;
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public ReporteService(PruebaRepository pruebaRepository, PosicionesRepository posicionesRepository, VehiculoRepository vehiculoRepository) {
        this.pruebaRepository = pruebaRepository;
        this.posicionesRepository = posicionesRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public DistanciaVehiculoResponse calcularDistanciaRecorrida(Integer idVehiculo, Date inicio, Date fin) {
        // Obtener posiciones del vehiculo en el periodo dado
        List<Posicion> posiciones = posicionesRepository.findByIdVehiculoAndFechaHoraBetween(idVehiculo, inicio, fin);
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con ID: " + idVehiculo));
        Double distanciaTotal = 0.0;

        if (posiciones.isEmpty()) {
            DistanciaVehiculoResponse response = new DistanciaVehiculoResponse(vehiculo, inicio, fin, distanciaTotal);
        }

        for (int i = 0; i < posiciones.size() - 1; i++) {
            Posicion pos1 = posiciones.get(i);
            Posicion pos2 = posiciones.get(i + 1);

            // Calcular Distancia entre pos1 y pos2 usando distancia eclidea
            distanciaTotal += calcularDistanciaEuclidea(pos1.getLatitud(), pos1.getLongitud(), pos2.getLatitud(), pos2.getLongitud());
        }
        DistanciaVehiculoResponse response = new DistanciaVehiculoResponse(vehiculo, inicio, fin, distanciaTotal);
        return response;
    }

    public List<PruebaDto> obtenerPruebasVehiculo(Integer idVehiculo) {
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con ID: " + idVehiculo));
        return vehiculo.getPruebas().stream().map(pruebaEntity -> new PruebaDto(pruebaEntity)).toList();
    }


    private Double calcularDistanciaEuclidea(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double dX = lat2 - lat1;
        Double dY = lon2 - lon2;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public Iterable<Prueba> getAll() { return pruebaRepository.findAll(); }



}
