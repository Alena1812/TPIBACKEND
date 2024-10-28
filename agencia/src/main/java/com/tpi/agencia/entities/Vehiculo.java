package com.tpi.agencia.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Vehiculos")
public class Vehiculo {
    @Id
    private Integer id;
    private String patente;
    private Integer anio;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo")
    private Set<Prueba> pruebas = new HashSet<>();

    @OneToMany(mappedBy = "vehiculo")
    private Set<Posicion> posicions = new HashSet<>();

    public Vehiculo() {
    }

    public Vehiculo(Integer id, String patente, Integer anio, Modelo modelo) {
        this.id = id;
        this.patente = patente;
        this.anio = anio;
        this.modelo = modelo;

        this.modelo.getVehiculos().add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Set<Prueba> getPruebas() {
        return pruebas;
    }

    public void setPruebas(Set<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    public Set<Posicion> getPosicions() {
        return posicions;
    }

    public void setPosicions(Set<Posicion> posicions) {
        this.posicions = posicions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(id, vehiculo.id) && Objects.equals(patente, vehiculo.patente) && Objects.equals(anio, vehiculo.anio) && Objects.equals(modelo, vehiculo.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patente, anio, modelo);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", anio=" + anio +
                ", modelo=" + modelo +
                '}';
    }
}