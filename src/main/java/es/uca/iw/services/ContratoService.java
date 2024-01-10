package es.uca.iw.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.uca.iw.repositories.ContratoRepository;
import es.uca.iw.repositories.ServicioRepository;
import jakarta.transaction.Transactional;
import es.uca.iw.repositories.ClienteRepository;

import es.uca.iw.domain.Contrato;
import es.uca.iw.domain.Fibra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import es.uca.iw.domain.Servicio;
import es.uca.iw.domain.Cliente;

import java.util.Date;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final ServicioRepository servicioRepository;
    private final ClienteRepository clienteRepository;

    public ContratoService(ContratoRepository contratoRepository, ServicioRepository servicioRepository, ClienteRepository clienteRepository) {
        this.contratoRepository = contratoRepository;
        this.servicioRepository = servicioRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Contrato> findAll(){
        return contratoRepository.findAll();
    }

    public Stream<Contrato> findAll(int offset, int limit) {
        return contratoRepository.findAll(PageRequest.of(offset / limit, limit)).getContent().stream();
    }

    public int count() {
        return (int) contratoRepository.count();
    }

    public Optional<Contrato> findByNumeroTelefono(String numeroTelefono){
        return contratoRepository.findByNumeroTelefono(numeroTelefono);
    }

    public Contrato save(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    @Transactional
    public Contrato contratar(UUID servicioId, UUID clienteId, Date fechaInicio, Date fechaFin, float costeMensual) {

        Servicio servicio = servicioRepository.findById(servicioId).orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + servicioId));
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        Contrato contrato = new Contrato();
        if(!(servicio instanceof Fibra)) contrato.setNumeroTelefonoAleatorio();
        contrato.setServicio(servicio);
        contrato.setCliente(cliente);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(fechaFin);
        contrato.setCosteMensual(costeMensual);

        return contratoRepository.save(contrato);
    }

    @Transactional
    public Contrato contratar(UUID servicioId, UUID clienteId, Date fechaInicio, Date fechaFin, float costeMensual, String numeroTelefono) {

        Servicio servicio = servicioRepository.findById(servicioId).orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + servicioId));
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        Contrato contrato = new Contrato();
        if(!(servicio instanceof Fibra)) contrato.setNumeroTelefono(numeroTelefono);
        contrato.setServicio(servicio);
        contrato.setCliente(cliente);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(fechaFin);
        contrato.setCosteMensual(costeMensual);

        return contratoRepository.save(contrato);
    }

    @Transactional
    public void descontratar(UUID contratoId) {
        Contrato contrato = contratoRepository.findById(contratoId).orElseThrow(() -> new RuntimeException("Contrato no encontrado con ID: " + contratoId));
        contrato.getCliente().removeContrato(contrato);
        contratoRepository.delete(contrato);
    }
}
