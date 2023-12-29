package es.uca.iw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.iw.domain.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    
}
