package es.uca.iw.domain;

//import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Movil extends Telefonia {

    private double datosMaximosGB;
    private double datosConsumidosGB; // campo calculado, cada dia se consumen x datos
   // private Set<Double> datosDiarios = new HashSet<Double>();
    private boolean roaming;

    public double getDatosMaximosGB() {
        return datosMaximosGB;
    }

    public void setDatosMaximosGB(double datosMaximosGB) {
        this.datosMaximosGB = datosMaximosGB;
    }

    //public Set<Double> getDatosDiarios() {
    //    return datosDiarios;
   // }

    public double getDatosConsumidosGB() {
        return datosConsumidosGB;
    }

    private void setDatosConsumidosGB() {
        double totalDatosConsumidos = 0.0;
       //for (double datosDiario : datosDiarios) {
       //     totalDatosConsumidos += datosDiario;
      // }
        this.datosConsumidosGB= totalDatosConsumidos;
    }

   // public void setDatosDiarios(double datosDiario) {
    //    datosDiarios.add(datosDiario);
    //    setDatosConsumidosGB();
    //}

    public boolean getRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }
}
