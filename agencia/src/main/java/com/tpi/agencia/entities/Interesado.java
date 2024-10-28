package com.tpi.agencia.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Interesados")
public class Interesado {
    @Id
    private Integer id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    private String documento;
    private String nombre;
    private String apellido;
    private Boolean restringido;

    @Column(name = "NRO_LICENCIA")
    private Integer nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private Date fechaVencimientoLicencia;

    @OneToMany(mappedBy = "interesado")
    private Set<Prueba> pruebas = new HashSet<>();

    public Interesado() {
    }

    public Interesado(Integer id, String tipoDocumento, String documento, String nombre, String apellido, Boolean restringido, Integer nroLicencia, Date fechaVencimientoLicencia) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.restringido = restringido;
        this.nroLicencia = nroLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public Boolean getRestringido() {
        return restringido;
    }

    public void setRestringido(Boolean restringido) {
        this.restringido = restringido;
    }

    public Integer getNroLicencia() {
        return nroLicencia;
    }

    public void setNroLicencia(Integer nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    public Date getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(Date fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
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
        Interesado that = (Interesado) o;
        return Objects.equals(id, that.id) && Objects.equals(tipoDocumento, that.tipoDocumento) && Objects.equals(documento, that.documento) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(restringido, that.restringido) && Objects.equals(nroLicencia, that.nroLicencia) && Objects.equals(fechaVencimientoLicencia, that.fechaVencimientoLicencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoDocumento, documento, nombre, apellido, restringido, nroLicencia, fechaVencimientoLicencia);
    }

    @Override
    public String toString() {
        return "Interesado{" +
                "id=" + id +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", documento=" + documento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", restringido=" + restringido +
                ", nroLicencia=" + nroLicencia +
                ", fechaVencimientoLicencia=" + fechaVencimientoLicencia +
                '}';
    }
}
