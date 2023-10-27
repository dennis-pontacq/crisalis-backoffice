package edu.bootcamp.backoffice.repository;


import org.springframework.data.repository.CrudRepository;

import edu.bootcamp.backoffice.model.cliente.Cliente;

public interface ClienteRepo extends CrudRepository<Cliente, Integer> {
    
}
