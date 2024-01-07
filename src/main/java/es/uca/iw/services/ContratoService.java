package es.uca.iw.services;
import org.springframework.stereotype.Service;

import es.uca.iw.repositories.ContratoRepository;
import es.uca.iw.repositories.ServicioRepository;
import jakarta.transaction.Transactional;
import es.uca.iw.repositories.ClienteRepository;

import es.uca.iw.domain.Contrato;

import java.util.List;
import java.util.UUID;

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


    @Transactional
    public Contrato contratar(UUID servicioId, UUID clienteId, Date fechaInicio, Date fechaFin, float costeMensual) {

        Servicio servicio = servicioRepository.findById(servicioId).orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + servicioId));
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        Contrato contrato = new Contrato();
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
        contratoRepository.delete(contrato);
    }
}
