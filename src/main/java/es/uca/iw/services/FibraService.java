package es.uca.iw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.uca.iw.domain.Fibra;
import es.uca.iw.domain.Servicio;
import es.uca.iw.repositories.FibraRepository;

@Service
public class FibraService {
    private final FibraRepository repository;

    public FibraService(FibraRepository repository){
        this.repository = repository;
    }

    public Fibra SaveFibra (Fibra fibra){
        return repository.save(fibra);
    }

    public List<Fibra> findAll(){
        return repository.findAll();
    }
}
