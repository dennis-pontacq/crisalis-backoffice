
package edu.bootcamp.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.bootcamp.backoffice.model.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "email")
	private String email;

	@Column(name = "pass")
	private String password;

	@Column(name = "nombre")
	private String nombre;
	
	public Usuario(UsuarioDto dto) {
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.nombre = dto.getNombre();
	}

	public UsuarioDto toDto() {
		return UsuarioDto 
				    .builder()
					.nombre(this.getNombre())
					.email(this.getEmail())
					.password(this.getPassword())
					.build();
	}
	
	/*
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	*/
}
