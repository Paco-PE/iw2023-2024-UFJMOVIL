package es.uca.iw.domain;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "servicio_movil")
@DiscriminatorValue("movil") // Valor discriminador para identificar la subclase
public class Movil extends Telefonia {

    public Movil(float precio, String name, int minutosMaximos, int llamadasMaximas,
            double datosMaximosGB, boolean roaming) {
        super(precio, name, minutosMaximos, llamadasMaximas);
        this.datosMaximosGB = datosMaximosGB;
        this.roaming = roaming;
    }

    private double datosMaximosGB;
    private double datosConsumidosGB; // campo calculado, cada dia se consumen x datos
    private Set<Double> datosDiarios = new HashSet<Double>();
    private boolean roaming;

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

    public boolean getRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }
}
