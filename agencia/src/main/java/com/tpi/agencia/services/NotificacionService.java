package com.tpi.agencia.services;

import com.tpi.agencia.dtos.PosicionDto;
import com.tpi.agencia.dtos.externos.NotificacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;


import org.springframework.web.client.RestTemplate;

@Service
public class NotificacionService {

    private final RestTemplate restTemplate;

    private final String notificacionRadioUrl = "http://localhost:8081/notificaciones/seguridad/radio-excedido/new";// URL del microservicio de notificación
    private final String notificacionZonaUrl = "http://localhost:8081/notificaciones/seguridad/zona-peligrosa/new";// URL del microservicio de notificación


    @Autowired
    public NotificacionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public void enviarMensajeRadioExcedido(PosicionDto radioExcedido) {
        restTemplate.postForObject(notificacionRadioUrl, radioExcedido, PosicionDto.class);
    }


    public void enviarMensajeZonaPeligrosa(PosicionDto zonaPeligrosa) {
        restTemplate.postForObject(notificacionZonaUrl, zonaPeligrosa, PosicionDto.class);
    }


}

