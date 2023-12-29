package es.uca.iw.services;

import org.springframework.stereotype.Service;

import es.uca.iw.repositories.ContratoRepository;

import es.uca.iw.domain.Contrato;

import java.util.List;

@Service
public class ContratoService {
    private final ContratoRepository repository;

    public ContratoService(ContratoRepository repository){
        this.repository = repository;
    }

    public List<Contrato> findAll(){
        return repository.findAll();
    }
}
