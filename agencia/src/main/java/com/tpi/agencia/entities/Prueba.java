package com.tpi.agencia.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Pruebas")
public class Prueba {
    @Id
    private Integer id;

    @Column(name = "FECHA_HORA_INICIO")
    private Date fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private Date fechaHoraFin;

    private String comentarios;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    private Interesado interesado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    private Empleado empleado;

    public Prueba() {
    }

    public Prueba(Integer id, Date fechaHoraInicio, Date fechaHoraFin, String comentarios, Vehiculo vehiculo, Interesado interesado, Empleado empleado) {
        this.id = id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentarios = comentarios;
        this.vehiculo = vehiculo;
        this.interesado = interesado;
        this.empleado = empleado;

        this.vehiculo.getPruebas().add(this);
        this.interesado.getPruebas().add(this);
        this.empleado.getPruebas().add(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prueba prueba = (Prueba) o;
        return Objects.equals(id, prueba.id) && Objects.equals(fechaHoraInicio, prueba.fechaHoraInicio) && Objects.equals(fechaHoraFin, prueba.fechaHoraFin) && Objects.equals(comentarios, prueba.comentarios) && Objects.equals(vehiculo, prueba.vehiculo) && Objects.equals(interesado, prueba.interesado) && Objects.equals(empleado, prueba.empleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaHoraInicio, fechaHoraFin, comentarios, vehiculo, interesado, empleado);
    }

    @Override
    public String toString() {
        return "Prueba{" +
                "id=" + id +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", comentarios='" + comentarios + '\'' +
                ", vehiculo=" + vehiculo +
                ", interesado=" + interesado +
                ", empleado=" + empleado +
                '}';
    }
}
