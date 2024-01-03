package es.uca.iw.domain;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Ajusta el nombre de la columna según tu esquema de base de datos
    private Cliente cliente;
    private Servicio servicio;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio(){
        return servicio;
    }

    public void setServicio(Servicio servicio){
        this.servicio = servicio;
    }

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getCosteMensual(){
        return costemensual;
    }

    public void setCosteMensual(Float costemensual){
        this.costemensual = costemensual;
    }
}
