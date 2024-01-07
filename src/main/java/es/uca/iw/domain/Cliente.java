package es.uca.iw.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Component
@Entity
@Table(name = "cliente")
@DiscriminatorValue("cliente")
public class Cliente extends User {
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Contrato> contratos;

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public void addContrato(Contrato contrato) {
        if (contratos == null) {
            contratos = new ArrayList<>();
        }
        contratos.add(contrato);
    }

    public void removeContrato(Contrato contrato) {
        contratos.remove(contrato);
        contrato.setCliente(null);
    }

    private String numeroTelefono;
    
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
