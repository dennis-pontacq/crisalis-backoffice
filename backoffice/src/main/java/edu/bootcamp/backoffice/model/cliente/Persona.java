package edu.bootcamp.backoffice.model.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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

	@Column(name = "email", nullable = true, length = 50)
    private String email;
    
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
