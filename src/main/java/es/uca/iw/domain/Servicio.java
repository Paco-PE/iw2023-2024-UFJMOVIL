package es.uca.iw.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float precio;

    private String name;

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
}
