package edu.bootcamp.backoffice.model.cliente;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import edu.bootcamp.backoffice.model.dto.ClienteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@DiscriminatorValue(value = "PER")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Persona extends Cliente {
    
	public Persona(Integer id, String nombre, String email) {
		super(id, nombre);
		setEmail(email);
	}

	public Persona(ClienteDto dto) {
		super(dto);
		email = dto.email; 
	}
	
	
	@Column(name = "email", nullable = true, length = 50)
    private String email;

	@Override
	protected ClienteDto completarAtributosEspecificos(ClienteDto dto) {
		dto.email = this.email;
		dto.tipo = "PER";
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
