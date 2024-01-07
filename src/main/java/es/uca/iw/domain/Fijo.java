package es.uca.iw.domain;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "servicio_fijo")
@DiscriminatorValue("fijo")
public class Fijo extends Telefonia{
    
}
