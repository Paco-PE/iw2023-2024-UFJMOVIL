package es.uca.iw.unit;

import es.uca.iw.domain.Role;
import es.uca.iw.domain.User;
import es.uca.iw.repositories.UserRepository;
import es.uca.iw.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDetailsService = new UserDetailsServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    public void loadUserByUsername_success() {
        User user = new User();
        user.setUsername("test");
        user.setHashedPassword("hashedPassword");
        user.setRoles(Collections.singleton(Role.ADMINISTRADOR));

        when(userRepository.findByUsername("test")).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername("test");

        assertEquals("test", userDetails.getUsername());
        assertEquals("hashedPassword", userDetails.getPassword());
    }

    @Test
    public void loadUserByUsername_notFound() {
        when(userRepository.findByUsername("test")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("test"));
    }

    @Test
    public void registerUser_success() {
        User user = new User();
        user.setUsername("test");
        user.setHashedPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertTrue(userDetailsService.registerUser(user, Role.ADMINISTRADOR));
        assertEquals("hashedPassword", user.getHashedPassword());
        assertTrue(user.getRoles().contains(Role.ADMINISTRADOR));
    }

    @Test
    public void registerUser_failure() {
        User user = new User();
        user.setUsername("test");
        user.setHashedPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        assertFalse(userDetailsService.registerUser(user, Role.ADMINISTRADOR));
    }
}