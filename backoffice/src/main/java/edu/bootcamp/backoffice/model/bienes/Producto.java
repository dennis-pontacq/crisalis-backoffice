package edu.bootcamp.backoffice.model.bienes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "producto")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto extends Ofrecido {

	public Producto(Integer id, String nombre, BigDecimal precio, BigDecimal costoSoporte) {
		super(id, nombre, precio);
		this.costoSoporte = costoSoporte;
	}

	@Column(name = "costo_soporte", nullable = true)
	BigDecimal costoSoporte;    
	
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
