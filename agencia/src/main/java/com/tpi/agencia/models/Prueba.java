package com.tpi.agencia.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
@Entity
@Data
@Table(name = "Pruebas")
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    private Empleado empleado;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    private Interesado interesado;

    @Column(name = "FECHA_HORA_INICIO")
    private Date fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private Date fechaHoraFin = null;

    private String comentarios = null;

    // Este metodo remplaza al @Setter de Lombok para que el mismo configure la relacion bidireccional con vehiculo.
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
        if(vehiculo != null){
            vehiculo.getPruebas().add(this);
        }
    }

    public void setEmpleado(Empleado empleado){
        this.empleado = empleado;
        if(empleado != null){
            empleado.getPruebas().add(this);
        }
    }

    public void setInteresado(Interesado interesado){
        this.interesado = interesado;
        if(interesado != null){
            interesado.getPruebas().add(this);
        }
    }

}
