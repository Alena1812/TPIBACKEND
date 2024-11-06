package com.tpi.agencia.services;

import com.tpi.agencia.dtos.RestriccionesDto;
import jakarta.persistence.Cacheable;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestriccionesService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${tpi-agencia.microservicio-restricciones.url}")
    private String urlRestricciones;

    @Value(("${tpi-agencia.microservicio-notificaciones.url}"))
    private String urlNotificaciones;

    @Cacheable("restrictionsApiCache")
    public RestriccionesDto getRestricciones() {
        return restTemplate.getForObject(urlRestricciones, RestriccionesDto.class);
    }
}