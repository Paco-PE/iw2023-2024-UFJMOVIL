package es.uca.iw.domain;

import java.util.HashSet;
import java.util.List;

public class Telefonia extends Servicio {

    private int minutosMaximos;
    private int minutosConsumidos; // atributo derivado y calculado
    private int llamadasMaximas;
    private int nLlamadasRealizadas;
    private HashSet<Llamada> llamadasRealizadas;
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

    public void setMinutosConsumidos(int minutosConsumidos) {
        this.minutosConsumidos = minutosConsumidos;
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

    public void setnLlamadasRealizadas(int nLlamadasRealizadas) {
        this.nLlamadasRealizadas = nLlamadasRealizadas;
    }

    public HashSet<Llamada> getLlamadasRealizadas() {
        return llamadasRealizadas;
    }

    public void setLlamadasRealizadas(HashSet<Llamada> llamadasRealizadas) {
        this.llamadasRealizadas = llamadasRealizadas;
    }

    public List<String> getNumerosBloqueados() {
        return numerosBloqueados;
    }

    public void setNumerosBloqueados(List<String> numerosBloqueados) {
        this.numerosBloqueados = numerosBloqueados;
    }
}
