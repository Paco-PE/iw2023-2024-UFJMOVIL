package es.uca.iw.domain;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.collection.spi.PersistentSet;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "servicio_telefonia")
@DiscriminatorValue("telefonia") // Valor discriminador para identificar la subclase
public class Telefonia extends Servicio {
    private int minutosMaximos;
    private int minutosConsumidos; // atributo derivado y calculado
    private int llamadasMaximas;
    private int nLlamadasRealizadas; // atributo derivado y calculado

    // En la clase Telefonia
    @OneToMany(mappedBy = "servicioTelefonia")
    private PersistentSet<Llamada> llamadasRealizadas;

    private List<String> numerosBloqueados;

    public int getMinutosMaximos() {
        return minutosMaximos;
    }

    public void setMinutosMaximos(int minutosMaximos) {
        this.minutosMaximos = minutosMaximos;
    }

    public int getMinutosConsumidos() {
        return minutosConsumidos;
    }

    public void setMinutosConsumidos() {
        int totalMinutos = 0;
        for (Llamada llamada : llamadasRealizadas) {
            totalMinutos += llamada.getDuracion();
        }
        this.minutosConsumidos = totalMinutos;
    }

    public int getLlamadasMaximas() {
        return llamadasMaximas;
    }

    public void setLlamadasMaximas(int llamadasMaximas) {
        this.llamadasMaximas = llamadasMaximas;
    }

    public int getnLlamadasRealizadas() {
        return nLlamadasRealizadas;
    }

    public void setnLlamadasRealizadas() {
        this.nLlamadasRealizadas = llamadasRealizadas.size();
    }

    public PersistentSet<Llamada> getLlamadasRealizadas() {
        return llamadasRealizadas;
    }

    public void setLlamadasRealizadas(PersistentSet<Llamada> llamadasRealizadas) {
        this.llamadasRealizadas = llamadasRealizadas;
    }

    public void registrarLlamada(Llamada llamada) {
        nLlamadasRealizadas++;
        llamadasRealizadas.add(llamada);
        minutosConsumidos += llamada.getDuracion();
    }

    public List<String> getNumerosBloqueados() {
        return numerosBloqueados;
    }

    public void setNumerosBloqueados(List<String> numerosBloqueados) {
        this.numerosBloqueados = numerosBloqueados;
    }

    public void bloquearNumero(String numeroBloq) {
        numerosBloqueados.add(numeroBloq);
    }

}
