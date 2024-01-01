package es.uca.iw.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Llamada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "telefonia_id") // Ajusta el nombre de la columna según tu esquema de base de datos
    private Telefonia servicioTelefonia;

    private Date fecha;
    private int duracion; // en minutos
    private String numero;

    public Llamada() {
    }

    public Llamada(Telefonia servicioTelefonia, Date fecha, int duracion, String numero) {
        this.servicioTelefonia = servicioTelefonia;
        this.fecha = fecha;
        this.duracion = duracion;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
