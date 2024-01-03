package es.uca.iw.services;

import es.uca.iw.repositories.ServicioRepository;
import jakarta.transaction.Transactional;
import es.uca.iw.domain.Servicio;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicioService {
    private final ServicioRepository repository;

    public ServicioService(ServicioRepository repository){
        this.repository = repository;
    }

    public Servicio SaveServicio (Servicio servicio){
        return repository.save(servicio);
    }

    public List<Servicio> findAll(){
        return repository.findAll();
    }

    public Optional<Servicio> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public void deleteServicio(Servicio servicio) {
        repository.delete(servicio);
    }

    
}
