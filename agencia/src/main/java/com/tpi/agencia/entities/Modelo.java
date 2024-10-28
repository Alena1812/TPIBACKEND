package com.tpi.agencia.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Modelos")
public class Modelo {
    @Id
    private Integer id;
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MARCA")
    private Marca marca;

    @OneToMany(mappedBy = "modelo")
    private Set<Vehiculo> vehiculos = new HashSet<>();

    public Modelo() {
    }

    public Modelo(Integer id, String descripcion, Marca marca) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;

        this.marca.getModelos().add(this);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modelo modelo = (Modelo) o;
        return Objects.equals(id, modelo.id) && Objects.equals(descripcion, modelo.descripcion) && Objects.equals(marca, modelo.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, marca);
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", marca=" + marca +
                '}';
    }
}
