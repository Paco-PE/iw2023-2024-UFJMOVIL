package es.uca.iw;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import es.uca.iw.domain.Role;
import es.uca.iw.domain.User;
import es.uca.iw.repositories.UserRepository;
import es.uca.iw.services.UserDetailsServiceImpl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "UFJMOVIL")
public class Application implements AppShellConfigurator {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner createAdminUser() {
        return args -> {
            try {
                userService.loadUserByUsername("admin");
            } catch (UsernameNotFoundException e) {
                User admin = new User();

                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setHashedPassword("admin");
                ((UserDetailsServiceImpl) userService).registerUser(admin, Role.ADMINISTRADOR);
            }
        };
    }
}
