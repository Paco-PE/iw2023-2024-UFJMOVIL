package es.uca.iw.domain;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "servicio_movil")
@DiscriminatorValue("movil")

public class Movil extends Telefonia {

    private double datosMaximosGB;
    private double datosConsumidosGB; // campo calculado, cada dia se consumen x datos
    private Set<Double> datosDiarios = new HashSet<Double>();

    public double getDatosMaximosGB() {
        return datosMaximosGB;
    }

    public void setDatosMaximosGB(double datosMaximosGB) {
        this.datosMaximosGB = datosMaximosGB;
    }

    public Set<Double> getDatosDiarios() {
        return datosDiarios;
    }

    public double getDatosConsumidosGB() {
        return datosConsumidosGB;
    }

    private void setDatosConsumidosGB() {
        double totalDatosConsumidos = 0.0;
        for (double datosDiario : datosDiarios) {
            totalDatosConsumidos += datosDiario;
        }
        this.datosConsumidosGB = totalDatosConsumidos;
    }

    public void setDatosDiarios(double datosDiario) {
        datosDiarios.add(datosDiario);
        setDatosConsumidosGB();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
