package com.tpi.notificaciones.controllers;

import com.tpi.notificaciones.dtos.NotificacionPromocionDto;
import com.tpi.notificaciones.dtos.NotificacionRadioDto;
import com.tpi.notificaciones.dtos.PosicionDto;
import com.tpi.notificaciones.models.Notificacion;
import com.tpi.notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    @Autowired
    public NotificacionController(NotificacionService service) {this.notificacionService = service;}

    // Guardar notificacion por radio excedido
//    @PostMapping("/seguridad/radio-excedido/new")
//    public ResponseEntity<?> notificaRadioExcedido(@RequestBody NotificacionRadioDto radioExcedido) {
//        // Verifica que el objeto recibido no sea null y tenga datos
//        if (radioExcedido == null || radioExcedido.get() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Las coordenadas no fueron enviadas correctamente.");
//        }
//
//        return ResponseEntity.ok(notificacionService.createRadio(radioExcedido));
//    }


    // Guardar notificacion por zona peligrosa
        @PostMapping("/seguridad/zona-peligrosa/new")
        public ResponseEntity<?> notificarZonaPeligrosa(
                @RequestBody PosicionDto zonaPeligrosa) {
            return ResponseEntity.ok(notificacionService.createZona(zonaPeligrosa));
        }

    @PostMapping("/promocion/new")
    public ResponseEntity<Notificacion> notificarPromocion(
            @RequestBody NotificacionPromocionDto promocion) {
        return ResponseEntity.ok(notificacionService.createPromocion(promocion));
    }

    //Obtener notificacion de promocion
    @GetMapping("/promocion")
    public ResponseEntity<?> getAllPromociones() {
        return ResponseEntity.ok(notificacionService.getAllPromociones());
    }

    //Obtener notificacion de radio excedido
    @GetMapping("/seguridad/radio-excedido")
    public ResponseEntity<?> getAllRadios() {
        return ResponseEntity.ok(notificacionService.getAllRadios());
    }

    //Obtener notificacion de zona peligrosa
    @GetMapping("/seguridad/zona-peligrosa")
    public ResponseEntity<?> getAllZonas() {
        return ResponseEntity.ok(notificacionService.getAllZonas());
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el procesamiento de la notificación: " + ex.getMessage());
        }
    }

}
