package es.uca.iw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uca.iw.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findAllUser();

}