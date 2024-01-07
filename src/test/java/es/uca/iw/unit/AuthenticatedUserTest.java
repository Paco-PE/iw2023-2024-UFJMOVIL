package es.uca.iw.unit;

import es.uca.iw.domain.User;
import es.uca.iw.repositories.UserRepository;
import es.uca.iw.security.AuthenticatedUser;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticatedUserTest {

    private UserRepository userRepository;
    private AuthenticationContext authenticationContext;
    private AuthenticatedUser authenticatedUser;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        authenticationContext = mock(AuthenticationContext.class);
        authenticatedUser = new AuthenticatedUser(authenticationContext, userRepository);
    }

    @Test
    public void testGetAuthenticatedUser() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");
        when(authenticationContext.getAuthenticatedUser(UserDetails.class)).thenReturn(Optional.of(userDetails));
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user).get());

        Optional<User> result = authenticatedUser.get();

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUsername());
    }

    @Test
    public void testLogout() {
        authenticatedUser.logout();
        verify(authenticationContext, times(1)).logout();
    }
}