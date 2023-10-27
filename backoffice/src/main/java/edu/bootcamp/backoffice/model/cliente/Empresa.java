package edu.bootcamp.backoffice.model.cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@DiscriminatorValue(value = "EMP")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Empresa extends Cliente {

    
    public Empresa(Integer id, String nombre, Date inicioActividad, EmpresaCategoria categoria, List<Persona> personas) {
		super(id, nombre);
		this.inicioActividad = inicioActividad;
		this.categoria = categoria;
		this.personas = personas;
	}

	@Column(name = "fecha_inicio", nullable = true)
    private Date inicioActividad;
    
	@Enumerated(value = EnumType.STRING)
	private EmpresaCategoria categoria;
	
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
   
	
    @ManyToMany (
        fetch = FetchType.LAZY
     )
     @JoinTable (
        name = "cliente_cliente",
        joinColumns = {
            @JoinColumn(name = "cliente_id")

        },
        inverseJoinColumns = {
            @JoinColumn(name = "cliente_id_persona", referencedColumnName ="id")
        }
     )
    private List<Persona> personas = new ArrayList<Persona>(); 
    
}
