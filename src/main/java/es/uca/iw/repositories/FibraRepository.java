package es.uca.iw.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.iw.domain.Fibra;

public interface FibraRepository extends JpaRepository<Fibra, UUID> {
    
}
