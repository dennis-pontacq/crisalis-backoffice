
package edu.bootcamp.backoffice.model.pedido;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import edu.bootcamp.backoffice.model.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @SequenceGenerator(
        name = "pedido_sequence",
        sequenceName = "pedido_sequence",
        allocationSize = 1,
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pedido_sequence"
    )
    private Integer id;

    private String observaciones;
    private Integer estado;
    
    @ManyToOne(
    		fetch = FetchType.EAGER,
    		optional = false
    )
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
	
    @OneToMany(
    		fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<PedidoItem> items = new HashSet<>();
    

}
