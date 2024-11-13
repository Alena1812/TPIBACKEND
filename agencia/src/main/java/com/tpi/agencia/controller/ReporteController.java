package com.tpi.agencia.controller;

import com.tpi.agencia.dtos.ErrorResponse;
import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.dtos.report.response.DistanciaVehiculoResponse;
import com.tpi.agencia.dtos.report.response.IncidentesResponse;
import com.tpi.agencia.dtos.report.response.IncidentesXEmpleado;
import com.tpi.agencia.dtos.report.response.PruebasResponse;
import com.tpi.agencia.services.ReporteService;
import com.tpi.agencia.services.RestriccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final ReporteService reporteService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //inyeccion de dependencias
    @Autowired
    public ReporteController(ReporteService reporteService, RestriccionesService externalApisService) {
        this.reporteService = reporteService;
    }

    @GetMapping(value = "/kilometros-vehiculo/{idVehiculo}")
    public ResponseEntity<?> getKilometrosVehiculo(
            @PathVariable Integer idVehiculo,
            @RequestParam("fechaDesde") String fechaDesde,
            @RequestParam("fechaHasta") String fechaHasta) {
        try {
            // Definir el formato de la fecha
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Convertir las fechas desde String (yyyy-MM-dd) a objetos Date
            Date desde = dateFormat.parse(fechaDesde);
            Date hasta = dateFormat.parse(fechaHasta);

            // Obtener los timestamps en milisegundos
            long desdeMillis = desde.getTime();
            long hastaMillis = hasta.getTime();

            // Crear objetos Date con los timestamps
            Date desdeDate = new Date(desdeMillis);
            Date hastaDate = new Date(hastaMillis);

            DistanciaVehiculoResponse response = reporteService.calcularDistanciaRecorrida(idVehiculo, desdeDate, hastaDate);
            return ResponseEntity.ok(response);
        } catch (ParseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al parsear la fecha: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al calcular la distancia: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    //anda
    @GetMapping("/detalle-pruebas/{idVehiculo}")
    public ResponseEntity<?> obtenerPruebasVehiculo(@PathVariable Integer idVehiculo) {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerPruebasVehiculo(idVehiculo);
            PruebasResponse response = new PruebasResponse(pruebas);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/incidentes")
    public ResponseEntity<?> obtenerIncidentes() {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerIncidentes();
            IncidentesResponse response = new IncidentesResponse(pruebas);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al obtener el reporte de incidentes: " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/incidentes/{idEmpleado}")
    public ResponseEntity<?> obtenerIncidentesEmpleado(@PathVariable Integer idEmpleado) {
        try {
            List<PruebaDto> pruebas = reporteService.obtenerIncidentesEmpleado(idEmpleado);
            IncidentesXEmpleado response = new IncidentesXEmpleado(idEmpleado, pruebas);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "Error al obtener el reporte de incidentes del empleado con el ID: " + idEmpleado + " " +  e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}

