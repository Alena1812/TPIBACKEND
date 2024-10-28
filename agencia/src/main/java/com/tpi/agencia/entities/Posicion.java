package com.tpi.agencia.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Posiciones")
public class Posicion {
    @Id
    private Integer id;

    @Column(name = "FECHA_HORA")
    private Date fechaHora;

    private Double latitud;
    private Double longitud;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    public Posicion() {
    }

    public Posicion(Integer id, Date fechaHora, Double latitud, Double longitud, Vehiculo vehiculo) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.vehiculo = vehiculo;

        this.vehiculo.getPosicions().add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return Objects.equals(id, posicion.id) && Objects.equals(fechaHora, posicion.fechaHora) && Objects.equals(latitud, posicion.latitud) && Objects.equals(longitud, posicion.longitud) && Objects.equals(vehiculo, posicion.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaHora, latitud, longitud, vehiculo);
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", vehiculo=" + vehiculo +
                '}';
    }
}
