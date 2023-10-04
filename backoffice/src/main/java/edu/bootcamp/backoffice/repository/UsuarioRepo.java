package edu.bootcamp.backoffice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.bootcamp.backoffice.model.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByNombre(String nombre);
	
	//@Query( native )
	Optional<Usuario> findByEmail(String email);
	
}
