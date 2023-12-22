package es.uca.iw.fakers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import es.uca.iw.domain.Cliente;

import com.github.javafaker.Faker;

// Servicio falso que genera datos de prueba
@Service
public class ClienteService {
    private List<Cliente> clientes;

    public ClienteService() {
        clientes = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente();
            cliente.setId(UUID.randomUUID());
            cliente.setUsername(faker.name().firstName());
            cliente.setEmail(faker.internet().emailAddress());
            clientes.add(cliente);
        }
    }

    public List<Cliente> findAll() {
        return clientes;
    }
}
