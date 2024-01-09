package es.uca.iw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.uca.iw.domain.Fibra;
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

    public Fibra UpdateFibra(Fibra fibra) {
        if (fibra.getId() != null && repository.existsById(fibra.getId())) {
            return repository.save(fibra);
        } else {
            throw new IllegalArgumentException("Fibra no encontrada");
        }
    }

    public List<Fibra> findAll(){
        return repository.findAll();
    }
}
