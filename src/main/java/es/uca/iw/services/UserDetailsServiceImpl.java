package es.uca.iw.services;

import es.uca.iw.repositories.ClienteRepository;
import es.uca.iw.repositories.UserRepository;
import es.uca.iw.domain.Cliente;
import es.uca.iw.domain.Role;
import es.uca.iw.domain.User;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.t;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(),
                    getAuthorities(user));
        }
    }

    public List<User> loadAll() {
        return userRepository.findAll();
    }

    public User loadUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean registerUser(User user, Role role) {
        try {
            user.setHashedPassword(passwordEncoder.encode(user.getHashedPassword()));
            user.addRole(role);
            user.setTipoUsuario(role.name());
            
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
