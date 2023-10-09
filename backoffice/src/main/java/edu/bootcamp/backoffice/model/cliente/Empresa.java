package edu.bootcamp.backoffice.model.cliente;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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

    
    public Empresa(Integer id, String nombre, Date inicioActividad, EmpresaCategoria categoria) {
		super(id, nombre);
		this.inicioActividad = inicioActividad;
		this.categoria = categoria;
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
    
}
