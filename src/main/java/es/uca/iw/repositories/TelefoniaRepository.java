package es.uca.iw.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.iw.domain.Telefonia;

public interface TelefoniaRepository extends JpaRepository<Telefonia, UUID> {
    
}
