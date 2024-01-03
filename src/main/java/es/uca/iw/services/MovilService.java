package es.uca.iw.services;

import org.springframework.stereotype.Service;

import es.uca.iw.repositories.MovilRepository;

@Service
public class MovilService {
    private final MovilRepository repository;

    public MovilService (MovilRepository repository){
        this.repository = repository;
    }
}
