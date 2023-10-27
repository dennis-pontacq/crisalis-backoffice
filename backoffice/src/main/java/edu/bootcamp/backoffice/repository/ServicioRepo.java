package edu.bootcamp.backoffice.repository;


import org.springframework.data.repository.CrudRepository;

import edu.bootcamp.backoffice.model.bienes.Servicio;

public interface ServicioRepo extends CrudRepository<Servicio, Integer> {
    
}
