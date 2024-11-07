package com.tpi.agencia.dtos;

import com.tpi.agencia.models.Empleado;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmpleadoDto {
    private Integer legajo;
    private String nombre;
    private String apellido;
    private Integer telefonoContacto;

    public EmpleadoDto(Empleado empleado) {
        this.legajo = empleado.getLegajo();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.telefonoContacto = empleado.getTelefonoContacto();
    }
}
