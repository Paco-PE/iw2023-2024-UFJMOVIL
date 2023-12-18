package es.uca.iw.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado extends User {
    private String idEmpleado;

    private Rol rol;
    // otros campos específicos de Empleado

    // getters y setters

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Rol getRol(){
        return rol;
    }

    public void setRol(Rol rol){
        this.rol = rol;
    }
    // otros getters y setters
}
