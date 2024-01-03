package es.uca.iw.services;

import org.springframework.stereotype.Service;

import es.uca.iw.domain.Telefonia;
import es.uca.iw.repositories.TelefoniaRepository;

@Service
public class TelefoniaService {
    private final TelefoniaRepository repository;

    public TelefoniaService (TelefoniaRepository repository){
        this.repository = repository;
    }
}
