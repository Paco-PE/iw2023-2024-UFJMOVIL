package es.uca.iw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.iw.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByActiveTrue();
    
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}