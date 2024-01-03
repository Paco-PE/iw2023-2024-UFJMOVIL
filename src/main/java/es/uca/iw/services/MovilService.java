package es.uca.iw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.uca.iw.domain.Movil;
import es.uca.iw.domain.Servicio;
import es.uca.iw.repositories.MovilRepository;

@Service
public class MovilService {
    private final MovilRepository repository;

    public MovilService (MovilRepository repository){
        this.repository = repository;
    }

    public Movil SaveMovil (Movil movil){
        return repository.save(movil);
    }

    public List<Movil> findAll(){
        return repository.findAll();
    }
}
