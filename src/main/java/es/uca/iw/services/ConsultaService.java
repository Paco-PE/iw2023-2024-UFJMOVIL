package es.uca.iw.services;

import es.uca.iw.domain.Consulta;
import es.uca.iw.repositories.ConsultaRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository){
        this.repository = repository;
    }

    public Consulta createConsulta(Consulta consulta){
        return repository.save(consulta);
    }

    public List<Consulta> findAll(){
        return repository.findAll();
    }
}