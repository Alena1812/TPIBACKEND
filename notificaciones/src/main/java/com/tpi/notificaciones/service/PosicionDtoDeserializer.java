package com.tpi.notificaciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.notificaciones.dtos.PosicionDto;
import org.springframework.stereotype.Service;

@Service
public class PosicionDtoDeserializer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PosicionDto deserializarPosicion(byte[] data) {
        try {
            // Deserializar desde byte[] (si los datos están en formato JSON)
            return objectMapper.readValue(data, PosicionDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize data", e);
        }
    }

    public PosicionDto deserializarPosicion(String jsonData) {
        try {
            // Deserializar desde String (si los datos están en formato JSON)
            return objectMapper.readValue(jsonData, PosicionDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize data", e);
        }
    }
}

