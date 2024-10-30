package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Empleados")
@Data
public class Empleado {
    @Id
    private Integer legajo;
    private String nombre;
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO")
    private Integer telefonoContacto;

    @OneToMany(mappedBy = "empleado")
    private Set<Prueba> pruebas = new HashSet<>();

}
