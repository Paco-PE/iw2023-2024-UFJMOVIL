package es.uca.iw.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String descripcion;

    private boolean resuelta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public boolean getResuelta(){
        return resuelta;
    }

    public void setResuelta(boolean resuelta){
        this.resuelta = resuelta;
    }

}
