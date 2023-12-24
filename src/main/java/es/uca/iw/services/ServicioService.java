package es.uca.iw.services;

import es.uca.iw.repositories.ServicioRepository;
import es.uca.iw.domain.Servicio;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioService {
    private final ServicioRepository repository;

    public ServicioService(ServicioRepository repository){
        this.repository = repository;
    }

    public List<Servicio> findAll(){
        return repository.findAll();
    }
}
