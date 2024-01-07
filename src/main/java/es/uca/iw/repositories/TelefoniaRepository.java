package es.uca.iw.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.uca.iw.domain.Telefonia;

public interface TelefoniaRepository extends JpaRepository<Telefonia, UUID> {
    @Query("SELECT t FROM Telefonia t WHERE TYPE(t) <> Movil")
    List<Telefonia> findAllFijo();
}
