package es.uca.iw.domain;

import java.util.Date;

public class Llamada {
    private Date fecha;
    private int duracion; //en minutos
    private String numero;

    public Llamada(Date fecha, int duracion, String numero) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.numero = numero;
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
