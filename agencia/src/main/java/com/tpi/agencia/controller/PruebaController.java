package com.tpi.agencia.controller;

import com.tpi.agencia.services.PruebaService;
import com.tpi.agencia.models.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import com.tpi.agencia.dtos.ErrorResponse;
import com.tpi.agencia.dtos.PruebaDto;
import com.tpi.agencia.models.Prueba;
import com.tpi.agencia.services.PruebaService;
import com.tpi.agencia.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {
    private final PruebaService service;

    @Autowired
    public PruebaController(PruebaService service) {
        this.service = service;
    }

    // Obtener todas las pruebas
    @GetMapping
    public ResponseEntity<Iterable<PruebaDto>> getAllPruebas() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtener prueba por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPruebaById(@PathVariable Integer id) {
        try {
            PruebaDto found = service.findById(id);
            return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            return createErrorResponse(HttpStatus.NOT_FOUND, "Not Found", e.getMessage());
        }
    }

    // Obtener pruebas en curso
    @GetMapping("/en-curso")
    public ResponseEntity<List<PruebaDto>> getPruebasEnCurso() {
        List<PruebaDto> pruebas = service.getPruebasEnCurso();
        return pruebas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pruebas);
    }

    // Crear una prueba
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody PruebaDto prueba) {
        try {
            PruebaDto nuevaPrueba = service.create(prueba);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPrueba);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage());
        }
    }

    // Modificar una prueba
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        try {
            PruebaDto updatedPrueba = service.updatePrueba(id, pruebaDto);
            return ResponseEntity.ok(updatedPrueba);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage());
        }
    }

    // Finalizar una prueba
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarPrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        try {
            Prueba updatedPrueba = service.finalizarPrueba(id, pruebaDto.getComentarios());
            return ResponseEntity.ok(updatedPrueba);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage());
        }
    }

    // Borrar una prueba
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrueba(@PathVariable Integer id) {
        try {
            service.deletePrueba(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return createErrorResponse(HttpStatus.NOT_FOUND, "Not Found", e.getMessage());
        }
    }

    // Método auxiliar para construir una respuesta de error
    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String error, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                error,
                message
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}

