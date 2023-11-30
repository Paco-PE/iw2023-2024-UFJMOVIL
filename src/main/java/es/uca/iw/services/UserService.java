package es.uca.iw.services;

import es.uca.iw.domain.User;
import es.uca.iw.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean registerUser(User user) {
        user.setPassword("codedpassword"); // TODO: encode password

        try {
            repository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public Optional<User> loadUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> loadUserById(UUID userId) {
        return repository.findById(userId);
    }

    public List<User> loadUsers() {
        return repository.findAll();
    }

    public void delete(User testUser) {
        repository.delete(testUser);
    }

<<<<<<< HEAD
    public boolean loginUser(User user) {

        // Comprobamos si el usuario existe
        Optional<User> existingUser = repository.findByUsername(user.getUsername());
    
        if (!existingUser.isPresent()) {
            return false;
        }
    
        // Comprobamos si la contraseña es correcta
        if (!existingUser.get().getPassword().equals(user.getPassword())) {
            return false;
        }
        
                
        // Iniciamos sesión al usuario
        //authenticationManager.login(existingUser.get());
    
        return true;
    }

=======
>>>>>>> 151fb5cb7eb5fdec4cc77043c93bdeca42bce17d
}
