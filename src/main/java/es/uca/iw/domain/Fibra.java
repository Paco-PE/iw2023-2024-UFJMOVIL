package es.uca.iw.domain;


@Component
@Entity
@Table(name = "servicio_fibra")
@DiscriminatorValue("fibra") // Valor discriminador para identificar la subclase
public class Fibra extends Servicio {
    public Fibra(float precio, String name, int velocidadContratadaMb) {
        super(precio, name);
        this.velocidadContratadaMb = velocidadContratadaMb;
    }

    private int velocidadContratadaMb;

    public int getVelocidadContratadaMb() {
        return velocidadContratadaMb;
    }

    public void setVelocidadContratadaMb(int velocidadContratadaMb) {
        this.velocidadContratadaMb = velocidadContratadaMb;
    }
}
