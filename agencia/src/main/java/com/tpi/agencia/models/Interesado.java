package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Interesados")
@Data
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

}
