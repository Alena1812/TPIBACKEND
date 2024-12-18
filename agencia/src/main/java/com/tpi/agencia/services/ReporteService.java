package com.tpi.agencia.services;

import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.dtos.externos.NotificacionRadioExcedidoDto;
import com.tpi.agencia.dtos.report.response.DistanciaVehiculoResponse;
import com.tpi.agencia.models.Posicion;
import com.tpi.agencia.models.Prueba;
import com.tpi.agencia.models.Vehiculo;
import com.tpi.agencia.repositories.PosicionesRepository;
import com.tpi.agencia.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpi.agencia.services.RestriccionesService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.Objects;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    private final PruebaRepository pruebaRepository;
    private final PosicionesRepository posicionesRepository;
    private final VehiculoRepository vehiculoRepository;
    private final RestriccionesService restriccionesService;

    @Autowired
    public ReporteService(PruebaRepository pruebaRepository, PosicionesRepository posicionesRepository, VehiculoRepository vehiculoRepository, RestriccionesService restriccionesService) {
        this.pruebaRepository = pruebaRepository;
        this.posicionesRepository = posicionesRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.restriccionesService = restriccionesService;
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
        Double dY = lon2 - lon1;
        return Math.sqrt(dX * dX + dY * dY);
    }

    private PruebaDto buscarPruebaDeNotificacion(NotificacionRadioExcedidoDto notificacion) {
        List<Prueba> pruebas = pruebaRepository.findPruebasByVehiculoId(notificacion.getIdVehiculo());

        Date fechaNotificacion = notificacion.getFechaNotificacion();
        Date fechaActual = new Date();

        List<Prueba> pruebasFiltradas = pruebas.stream()
                .filter(prueba -> {
                    Date fechaHoraInicio = prueba.getFechaHoraInicio();
                    Date fechaHoraFin = prueba.getFechaHoraFin();

                    return (fechaHoraFin == null &&
                            fechaNotificacion.after(fechaHoraInicio) &&
                            fechaNotificacion.before(fechaActual)) ||
                            (fechaHoraFin != null &&
                                    fechaNotificacion.after(fechaHoraInicio) &&
                                    fechaNotificacion.before(fechaHoraFin));
                })
                .collect(Collectors.toList());

        if (!pruebasFiltradas.isEmpty()) {
            return new PruebaDto(pruebasFiltradas.get(0));
        }

        if (!pruebas.isEmpty()) {
            return new PruebaDto(pruebas.get(0));
        }

        return null;
    }

    private PruebaDto buscarPruebaDeNotificacionEmpleado(NotificacionRadioExcedidoDto notificacion, Integer idEmpleado) {
        System.out.println(notificacion);
        System.out.println(idEmpleado);
        Prueba prueba = pruebaRepository.findPruebaByVehiculoIdAndEmpleado(notificacion.getIdVehiculo(), idEmpleado);
        return new PruebaDto(prueba);
    }

    public List<PruebaDto> obtenerIncidentesEmpleado(Integer idEmpleado) {
        List<NotificacionRadioExcedidoDto> notificaciones = restriccionesService.getNotificacionesRadioExcedido();
        return notificaciones.stream()
                .map(this::buscarPruebaDeNotificacion)
                .filter(Objects::nonNull)
                .filter(prueba -> prueba.getEmpleado().getLegajo().equals(idEmpleado)) // Filtra después
                .collect(Collectors.toList());
    }
    public List<PruebaDto> obtenerIncidentes() {
        List<NotificacionRadioExcedidoDto> notificaciones = restriccionesService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(this::buscarPruebaDeNotificacion)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Iterable<Prueba> getAll() { return pruebaRepository.findAll(); }

}
