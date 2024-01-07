package es.uca.iw.services;

import es.uca.iw.repositories.ClienteRepository;
import es.uca.iw.repositories.UserRepository;
import es.uca.iw.domain.Cliente;
import es.uca.iw.domain.Role;
import es.uca.iw.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService implements UserDetailsService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> maybeCliente = clienteRepository.findByUsername(username);
        User user = maybeCliente.orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(),
                    getAuthorities(user));
        }
    }

    public List<Cliente> loadAll() {
        return clienteRepository.findAll();
    }

    public User loadClienteById(UUID id) {
        return clienteRepository.findById(id).orElse(null);
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    public boolean registerUser(Cliente cliente) {
        try {
            cliente.setHashedPassword(passwordEncoder.encode(cliente.getHashedPassword()));
            cliente.addRole(Role.CLIENTE);
            clienteRepository.save(cliente);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
