package edu.bootcamp.backoffice.model.bienes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ofrecido")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ofrecido {
    @Id
    @SequenceGenerator(
        name = "ofrecido_sequence",
        sequenceName = "ofrecido_sequence",
        allocationSize = 1,
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ofrecido_sequence"
    )
    private Integer id;

    private String nombre;
    private BigDecimal precio;
    
    /*
    @OneToMany(
    		fetch = FetchType.EAGER,
    		cascade = CascadeType.ALL
    )
    private Set<PedidoItem>items = new HashSet<>();
	*/

    /*
    @ManyToMany(mappedBy = "productos")
    private List<Cliente> clientes;
    */
    
}
