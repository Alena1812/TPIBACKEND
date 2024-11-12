package com.tpi.agencia.dtos.report.response;

import com.tpi.agencia.dtos.PruebaConIncidentesDto;
import com.tpi.agencia.dtos.PruebaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class IncidentesResponse extends ReportResponse {
    private Integer totalIncidentes;
    private List<PruebaConIncidentesDto> pruebasConIncidentes;

    public IncidentesResponse(List<PruebaDto> pruebasConIncidentes) {
        super("Reporte de Incidentes", "Reporte de las pruebas que tienen accidentes registrados.");

        // Inicializa el conteo total de incidentes
        this.totalIncidentes = pruebasConIncidentes.size();

        // Agrupa las pruebas por ID y cuenta los incidentes
        Map<Integer, List<PruebaDto>> pruebasAgrupadas = pruebasConIncidentes.stream()
                .collect(Collectors.groupingBy(PruebaDto::getId));

        // Crea una lista de PruebaConIncidentesDto usando el conteo de incidentes
        this.pruebasConIncidentes = pruebasAgrupadas.entrySet().stream()
                .map(entry -> new PruebaConIncidentesDto(
                        entry.getValue().get(0), // Primer elemento de la lista agrupada para representar la prueba
                        entry.getValue().size()  // Total de incidentes en esa prueba
                ))
                .collect(Collectors.toList());
    }
}
