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

    //inyeccion de dependencias    @Autowired
    public PruebaController(PruebaService service) {
        this.service = service;
    }

    // Obtener todas las pruebas
    @GetMapping
    public ResponseEntity<Iterable<PruebaDto>> getAllPruebas() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtener prueba por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getPruebaById(@PathVariable Integer id) {
        try {
            PruebaDto found = service.findById(id);
            System.out.println(found);
            return ResponseEntity.ok(found);
        } catch (ServiceException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    // Obtener pruebas en curso
    @GetMapping("/en-curso")
    public ResponseEntity<List<PruebaDto>> getPruebasEnCurso() {
        List<PruebaDto> pruebas = service.getPruebasEnCurso();

        if (pruebas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pruebas);
    }

    // crear una prueba
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody PruebaDto prueba) {
        try {
            PruebaDto nuevaPrueba = service.create(prueba);
            return ResponseEntity.ok(nuevaPrueba);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // modificar una prueba
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        try {
            PruebaDto updatedPrueba = service.updatePrueba(id, pruebaDto);
            return ResponseEntity.ok(updatedPrueba);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarPrueba(@PathVariable Integer id, @RequestBody PruebaDto pruebaDto) {
        try {
            PruebaDto updatedPrueba = service.finalizarPrueba(id, pruebaDto.getComentarios());
            return ResponseEntity.ok(updatedPrueba);
        } catch (IllegalStateException e) { //Manejo para prueba ya finalizada
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.CONFLICT.value(),
                    "Conflict",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (IllegalArgumentException e) { // Manejo para prueba no encontrada
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // borrar una prueba
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrueba(@PathVariable Integer id) {
        try {
            service.deletePrueba(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
