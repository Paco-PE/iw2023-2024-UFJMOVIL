package es.uca.iw.domain;
import org.springframework.stereotype.Component;

import jakarta.persistence.Id;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorType;

@Component
@Entity
@Table(name = "servicio") // Tabla única para todas las clases de la jerarquía
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_servicio", discriminatorType = DiscriminatorType.STRING)

public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float precio;

    private String name;

    private String tiposervicio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getPrecio(){
        return precio;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

    public String getTipoServicio(){
        return tiposervicio;
    }

    public void setTipoServicio(String tipoServicio){
        this.tiposervicio = tipoServicio;
    }
}
