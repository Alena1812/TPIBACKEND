package com.tpi.agencia.services;

import com.tpi.agencia.dtos.RestriccionesDto;
import com.tpi.agencia.dtos.externos.NotificacionRadioExcedidoDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestriccionesService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${tpi-agencia.microservicio-restricciones.url}")
    private String urlRestricciones;

    @Value("${tpi-agencia.microservicio-notificaciones.url")
    private String urlNotificaciones;

    @Cacheable("restrictionsApiCache")
    public RestriccionesDto getRestricciones() {
        return restTemplate.getForObject(urlRestricciones, RestriccionesDto.class);
    }
    public List<NotificacionRadioExcedidoDto> getNotificacionesRadioExcedido() {
        NotificacionRadioExcedidoDto[] notificacionesArray = restTemplate.getForObject(urlNotificaciones + "/notificaciones/seguridad/radio", NotificacionRadioExcedidoDto[].class);
        return Arrays.asList(notificacionesArray);
    }
}
