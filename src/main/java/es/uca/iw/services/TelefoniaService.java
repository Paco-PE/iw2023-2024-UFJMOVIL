package es.uca.iw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.uca.iw.domain.Telefonia;
import es.uca.iw.repositories.TelefoniaRepository;

@Service
public class TelefoniaService {
    private final TelefoniaRepository repository;

    public TelefoniaService (TelefoniaRepository repository){
        this.repository = repository;
    }

    public Telefonia SaveTelefonia (Telefonia telefonia){
        return repository.save(telefonia);
    }

    public List<Telefonia> findAll(){
        return repository.findAll();
    }

    public Telefonia UpdateTelefonia(Telefonia telefonia) {
        if (telefonia.getId() != null && repository.existsById(telefonia.getId())) {
            return repository.save(telefonia);
        } else {
            throw new IllegalArgumentException("Fijo no encontrado");
        }
    }

    public List<Telefonia> findAllFijo(){
        return repository.findAllFijo();
    }
}
