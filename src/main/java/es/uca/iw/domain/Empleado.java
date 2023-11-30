package es.uca.iw.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado extends User {
    private String idEmpleado;
    // otros campos específicos de Empleado

    // getters y setters

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    // otros getters y setters
}
