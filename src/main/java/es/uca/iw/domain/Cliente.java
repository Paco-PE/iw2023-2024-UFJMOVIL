package es.uca.iw.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends User {
    private String numeroTelefono;
    // otros campos específicos de Cliente

    // getters y setters

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    // otros getters y setters
}