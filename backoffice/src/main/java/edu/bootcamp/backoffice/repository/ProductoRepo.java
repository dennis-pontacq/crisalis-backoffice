package edu.bootcamp.backoffice.repository;


import org.springframework.data.repository.CrudRepository;

import edu.bootcamp.backoffice.model.Producto;

public interface ProductoRepo extends CrudRepository<Producto, Integer> {
    
}
