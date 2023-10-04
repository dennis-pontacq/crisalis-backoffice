package edu.bootcamp.backoffice.model.pedido;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import edu.bootcamp.backoffice.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pedidoItem")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItem {
    @Id
    @SequenceGenerator(
        name = "pedido_item_sequence",
        sequenceName = "pedido_item_sequence",
        allocationSize = 1,
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pedido_item_sequence"
    )
    private Integer id;

    private BigDecimal cantidad;
    private BigDecimal precio;
    
    @ManyToOne(
    		fetch = FetchType.LAZY,
    		optional = false
    )
    @JoinColumn(name = "producto_id")
    private Producto producto;

    
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    @JoinColumn(
        name = "pedido_id"
    )
    private Pedido pedido;
    
}
