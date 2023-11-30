package es.uca.iw.controllers;

import es.uca.iw.domain.User;
import es.uca.iw.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping("/api/users")
    public List<User> all() {
        return service.loadActiveUsers();
    }

    @PostMapping("/api/users")
    void newUser(@RequestBody User newUser) {
        service.registerUser(newUser);

    }

    // Single item

    @GetMapping("/api/users/{id}")
    User one(@PathVariable String id) {
        // TODO deal with invalid UUID
        return service.loadUserById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @PutMapping("/api/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

        // TODO
        return newUser;
    }

    @DeleteMapping("/api/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        // TODO
    }
}
