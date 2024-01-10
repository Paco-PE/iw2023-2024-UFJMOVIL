package es.uca.iw.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;

@Entity
@Table(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public float getCosteMensual(){
        return costemensual;
    }

    public void setCosteMensual(Float costemensual){
        this.costemensual = costemensual;
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
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        sb.append("6");
        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public boolean roaming;

    public boolean isRoaming() {
        return roaming;
    }
    
    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    private List<String> numerosBloqueados;

    public List<String> getNumerosBloqueados() {
        return numerosBloqueados;
    }
    
    public void setNumerosBloqueados(List<String> numerosBloqueados) {
        this.numerosBloqueados = numerosBloqueados;
    }

    public boolean addNumeroBloqueado(String numeroTelefono){
        if(numerosBloqueados == null) numerosBloqueados = new ArrayList<>();
        if(numerosBloqueados.contains(numeroTelefono)) {
            return false;
        } else {
            return this.numerosBloqueados.add(numeroTelefono);
        }
    }
}
