package es.uca.iw.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fibra")
public class Fibra extends Servicio {
    private int velocidadContratadaMb;

    public int getVelocidadContratadaMb() {
        return velocidadContratadaMb;
    }

    public void setVelocidadContratadaMb(int velocidadContratadaMb) {
        this.velocidadContratadaMb = velocidadContratadaMb;
    }
}
