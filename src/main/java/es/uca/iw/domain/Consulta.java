package es.uca.iw.domain;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String descripcion;

    private boolean resuelta;

    private String emailcontacto;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getResuelta() {
        return resuelta;
    }

    public void setResuelta(boolean resuelta) {
        this.resuelta = resuelta;
    }

    public String getEmailContacto() {
        return emailcontacto;
    }

    public void setEmailContacto(String emailcontacto) {
        this.emailcontacto = emailcontacto;
    }

}
