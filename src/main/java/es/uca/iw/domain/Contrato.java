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
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    public Servicio getServicio(){
        return servicio;
    }

    public void setServicio(Servicio servicio){
        this.servicio = servicio;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private Date fechaInicio;
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    private Date fechaFin;
    
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    private float costemensual;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getCosteMensual(){
        return costemensual;
    }

    private String numeroTelefono;

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefonoAleatorio() {
        String numeroTelefono = generateRandomPhoneNumber();
        this.numeroTelefono = numeroTelefono;
    }

    private String generateRandomPhoneNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("6");
        for (int i = 0; i < 8; i++) {
            int digit = (int) (Math.random() * 10);
            sb.append(digit);
        }
        return sb.toString();
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setCosteMensual(Float costemensual){
        this.costemensual = costemensual;
    }
}
