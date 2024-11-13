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
        Double dY = lon2 - lon2;
        return Math.sqrt(dX * dX + dY * dY);
    }

    private PruebaDto buscarPruebaDeNotificacion(NotificacionRadioExcedidoDto notificacion) {
        System.out.println(notificacion.getIdVehiculo());
        System.out.println(notificacion.getFechaNotificacion());
        Prueba prueba = pruebaRepository.findPruebaByVehiculoIdAndFechaNotificacion(notificacion.getIdVehiculo(), notificacion.getFechaNotificacion());
        System.out.println(prueba);
        return new PruebaDto(prueba);
    }

    private PruebaDto buscarPruebaDeNotificacionEmpleado(NotificacionRadioExcedidoDto notificacion, Integer idEmpleado) {
        Prueba prueba = pruebaRepository.findPruebaByVehiculoIdAndFechaNotificacionAndEmpleado(notificacion.getIdVehiculo(), notificacion.getFechaNotificacion(), idEmpleado);
        return new PruebaDto(prueba);
    }

    public List<PruebaDto> obtenerIncidentesEmpleado(Integer idEmpleado) {
        List<NotificacionRadioExcedidoDto> notificaciones = restriccionesService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(notificacion -> buscarPruebaDeNotificacionEmpleado(notificacion, idEmpleado))
                .collect(Collectors.toList());
    }
    public List<PruebaDto> obtenerIncidentes() {
        List<NotificacionRadioExcedidoDto> notificaciones = restriccionesService.getNotificacionesRadioExcedido();

        return notificaciones.stream()
                .map(this::buscarPruebaDeNotificacion)
                .collect(Collectors.toList());
    }

    public Iterable<Prueba> getAll() { return pruebaRepository.findAll(); }

}
