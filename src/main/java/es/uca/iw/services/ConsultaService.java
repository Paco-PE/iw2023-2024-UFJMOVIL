package es.uca.iw.services;

import es.uca.iw.domain.Consulta;
import es.uca.iw.repositories.ConsultaRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Stream;

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

    public Stream<Consulta> findAll(int offset, int limit) {
        return repository.findAll(PageRequest.of(offset / limit, limit)).getContent().stream();
    }

    public int count() {
        return (int) repository.count();
    }

    public Consulta saveConsulta(Consulta consulta){
        return repository.save(consulta);
    }
}