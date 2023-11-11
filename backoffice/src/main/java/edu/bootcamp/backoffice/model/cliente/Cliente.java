package edu.bootcamp.backoffice.model.cliente;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import edu.bootcamp.backoffice.model.dto.ClienteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name = "tipo",
	discriminatorType = DiscriminatorType.STRING
)
@Table(name = "cliente")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class Cliente {
    
	@Id
    @SequenceGenerator(
        name = "cliente_sequence",
        sequenceName = "cliente_sequence",
        allocationSize = 1,
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "cliente_sequence"
    )
    private Integer id;
	
	private String nombre;

	
	protected Cliente(ClienteDto dto) {
		this.id = dto.id;
		this.nombre = dto.nombre;
		
	}

	
	protected abstract ClienteDto completarAtributosEspecificos(ClienteDto dto);

	public ClienteDto toDto() {
    	
    	ClienteDto dto = new ClienteDto();
    	dto.id = id;
    	dto.nombre = nombre;
    	
    	dto = completarAtributosEspecificos(dto);
    	
    	return dto; 
    	
    }
	
	
    /*
    @ManyToMany (
        fetch = FetchType.LAZY
     )
     @JoinTable (
        name = "cliente_producto",
        joinColumns = {
            @JoinColumn(name = "cliente_id")

        },
        inverseJoinColumns = {
            @JoinColumn(name = "producto_id")
        }
     )
    private List<Producto> productos = new ArrayList<Producto>(); 
    */
}
