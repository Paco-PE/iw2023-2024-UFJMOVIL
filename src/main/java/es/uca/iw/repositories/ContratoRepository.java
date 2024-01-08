package es.uca.iw.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.iw.domain.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
    public List<Contrato> findByClienteId(UUID clienteId);
}
