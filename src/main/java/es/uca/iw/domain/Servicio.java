package es.uca.iw.domain;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorType;

@Entity
@Table(name = "servicio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_servicio", discriminatorType = DiscriminatorType.STRING)

public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private float precio;

    private String name;

    private String tiposervicio;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipoServicio() {
        return tiposervicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tiposervicio = tipoServicio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Servicio)) {
            return false;
        }
        Servicio servicio = (Servicio) obj;
        return Objects.equals(id, servicio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
