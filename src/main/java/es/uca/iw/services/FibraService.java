package es.uca.iw.services;

import org.springframework.stereotype.Service;

import es.uca.iw.repositories.FibraRepository;

@Service
public class FibraService {
    private final FibraRepository repository;

    public FibraService(FibraRepository repository){
        this.repository = repository;
    }
}
