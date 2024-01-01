package es.uca.iw.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "servicio_fibra")
@DiscriminatorValue("fibra") // Valor discriminador para identificar la subclase

public class Fibra extends Servicio {
    private float velocidadContratadaMb;

    public float getVelocidadContratadaMb() {
        return velocidadContratadaMb;
    }

    public void setVelocidadContratadaMb(float velocidadContratadaMb) {
        this.velocidadContratadaMb = velocidadContratadaMb;
    }
}
