package com.tpi.agencia.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Empleados")
public class Empleado {
    @Id
    private Integer legajo;
    private String nombre;
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO")
    private Integer telefonoContacto;

    @OneToMany(mappedBy = "empleado")
    private Set<Prueba> pruebas = new HashSet<>();

    public Empleado() {
    }

    public Empleado(Integer legajo, String nombre, String apellido, Integer telefonoContacto) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonoContacto = telefonoContacto;
    }

    public Integer getLegajo() {
        return legajo;
    }

    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(Integer telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Set<Prueba> getPruebas() {
        return pruebas;
    }

    public void setPruebas(Set<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(legajo, empleado.legajo) && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellido, empleado.apellido) && Objects.equals(telefonoContacto, empleado.telefonoContacto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(legajo, nombre, apellido, telefonoContacto);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "legajo=" + legajo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefonoContacto=" + telefonoContacto +
                '}';
    }
}
