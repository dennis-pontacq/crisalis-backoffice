package edu.bootcamp.backoffice.repository.pedido;


import org.springframework.data.repository.CrudRepository;

import edu.bootcamp.backoffice.model.pedido.Pedido;



public interface PedidoRepo extends CrudRepository<Pedido, Integer> {
    
}
