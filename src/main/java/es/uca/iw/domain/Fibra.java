package es.uca.iw.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fibra")
public class Fibra extends Servicio {
    private float velocidadContratadaMb;

    public float getVelocidadContratadaMb() {
        return velocidadContratadaMb;
    }

    public void setVelocidadContratadaMb(float velocidadContratadaMb) {
        this.velocidadContratadaMb = velocidadContratadaMb;
    }
}
