package com.tpi.notificaciones.service;

import com.tpi.notificaciones.dtos.NotificacionPromocionDto;
import com.tpi.notificaciones.dtos.NotificacionRadioDto;
import com.tpi.notificaciones.dtos.NotificacionZonaDto;
import com.tpi.notificaciones.dtos.PosicionDto;
import com.tpi.notificaciones.models.NotificacionPromocion;
import com.tpi.notificaciones.models.NotificacionRadio;
import com.tpi.notificaciones.models.NotificacionZona;
import com.tpi.notificaciones.repositories.NotificacionPromocionRepository;
import com.tpi.notificaciones.repositories.NotificacionRadioRepository;
import com.tpi.notificaciones.repositories.NotificacionZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

@Service
public class NotificacionService {

    private final NotificacionPromocionRepository promocionRepository;
    private final NotificacionRadioRepository radioRepository;
    private final NotificacionZonaRepository zonaRepository;

    @Autowired
    public NotificacionService(
            NotificacionPromocionRepository promocionRepository,
            NotificacionRadioRepository radioRepository,
            NotificacionZonaRepository zonaRepository) {
        this.promocionRepository = promocionRepository;
        this.radioRepository = radioRepository;
        this.zonaRepository = zonaRepository;
    }

    // Crear notificación de promoción
    public NotificacionPromocion createPromocion(NotificacionPromocionDto promocion) {
        NotificacionPromocion nuevaPromocion = buildNotificacionPromocionFromDto(promocion);
        return promocionRepository.save(nuevaPromocion);
    }

    // Crear notificación de radio excedido
    public NotificacionRadio createRadio(PosicionDto posicion) {
        NotificacionRadio nuevoRadio = buildNotificacionRadioFromDto(posicion);
        return radioRepository.save(nuevoRadio);
    }

    // Crear notificación de zona peligrosa
    public NotificacionZona createZona(PosicionDto posicion) {
        NotificacionZona nuevaZona = buildNotificacionZonaFromDto(posicion);
        return zonaRepository.save(nuevaZona);
    }

    // Métodos para obtener todas las notificaciones de cada tipo
    public Iterable<NotificacionPromocionDto> getAllPromociones() {
        Iterable<NotificacionPromocion> promociones =  promocionRepository.findAll();
        return StreamSupport.stream(promociones.spliterator(), false).map(NotificacionPromocionDto::new).toList();
    }

    public Iterable<NotificacionRadioDto> getAllRadios() {
        Iterable<NotificacionRadio> radios = radioRepository.findAll();
        return StreamSupport.stream(radios.spliterator(), false).map(NotificacionRadioDto::new).toList();
    }

    public Iterable<NotificacionZonaDto> getAllZonas() {
        Iterable<NotificacionZona> zonas = zonaRepository.findAll();
        return StreamSupport.stream(zonas.spliterator(), false).map(NotificacionZonaDto::new).toList();
    }

    private NotificacionPromocion buildNotificacionPromocionFromDto(NotificacionPromocionDto promocionDto) {
        NotificacionPromocion promocion = new NotificacionPromocion();
        promocion.setFechaNotificacion(LocalDateTime.now());
        promocion.setCodigoPromocion(promocionDto.getCodigoPromocion());
        promocion.setTexto(promocionDto.getTexto());
        promocion.setFechaExpiracion(promocionDto.getFechaExpiracion());
        return promocion;
    }

    private NotificacionRadio buildNotificacionRadioFromDto(PosicionDto posicion) {
        NotificacionRadio radioExcedido = new NotificacionRadio();
        radioExcedido.setFechaNotificacion(LocalDateTime.now());
        radioExcedido.setLatitud(posicion.getCoordenadas().getLatitud());
        radioExcedido.setLongitud(posicion.getCoordenadas().getLongitud());
        radioExcedido.setIdVehiculo(posicion.getVehiculo().getId());
        radioExcedido.setTexto(posicion.getTexto());
        return radioExcedido;
    }

    private NotificacionZona buildNotificacionZonaFromDto(PosicionDto posicion) {
        NotificacionZona zonaPeligrosa = new NotificacionZona();
        zonaPeligrosa.setFechaNotificacion(LocalDateTime.now());
        zonaPeligrosa.setNivelPeligro("ALTO");
        zonaPeligrosa.setLatitud(posicion.getCoordenadas().getLatitud());
        zonaPeligrosa.setLongitud(posicion.getCoordenadas().getLongitud());
        zonaPeligrosa.setIdVehiculo(posicion.getVehiculo().getId());
        zonaPeligrosa.setTexto(posicion.getTexto());
        return zonaPeligrosa;
    }
}
