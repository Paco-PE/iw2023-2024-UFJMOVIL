package es.uca.iw.domain;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date fechaInicio;
    private Date fechaFin;
    private float costemensual;

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCosteMensual(){
        return costemensual;
    }

    public void setCosteMensual(Float costemensual){
        this.costemensual = costemensual;
    }
}
