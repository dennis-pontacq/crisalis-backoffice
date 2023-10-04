package edu.bootcamp.backoffice.repository.pedido;


import org.springframework.data.repository.CrudRepository;

import edu.bootcamp.backoffice.model.pedido.PedidoItem;



public interface PedidoItemRepo extends CrudRepository<PedidoItem, Integer> {
    
}
