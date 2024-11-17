package com.tpi.agencia.services;

import com.tpi.agencia.dtos.PosicionDto;
import com.tpi.agencia.dtos.externos.NotificacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;


import org.springframework.web.client.RestTemplate;

@Service
public class NotificacionService {

    private final RestTemplate restTemplate;

    private final String notificacionUrl = "http://localhost:8081/notificaciones";// URL del microservicio de notificación

    @Autowired
    public NotificacionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void enviarMensajeRadioExcedido(PosicionDto mensaje) {
        NotificacionDto notificacion = new NotificacionDto(
                null, LocalDateTime.now(),
                "El vehículo ha excedido el radio permitido: " + mensaje
        );
        enviarNotificacion(notificacion);
    }


    public void enviarMensajeZonaPeligrosa(PosicionDto mensaje) {
        NotificacionDto notificacion = new NotificacionDto(
                null, LocalDateTime.now(),
                "El vehículo ha ingresado a una zona peligrosa: " + mensaje
        );
        enviarNotificacion(notificacion);
    }


    private void enviarNotificacion(NotificacionDto notificacionDto) {
            restTemplate.postForObject(notificacionUrl, notificacionDto, NotificacionDto.class);
    }

}

