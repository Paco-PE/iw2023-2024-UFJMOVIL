package es.uca.iw.repositories;

import es.uca.iw.domain.Cliente;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    
}
