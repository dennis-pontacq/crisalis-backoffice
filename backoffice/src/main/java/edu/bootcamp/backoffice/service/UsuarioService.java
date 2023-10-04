package edu.bootcamp.backoffice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.bootcamp.backoffice.exception.custom.EmptyElementException;
import edu.bootcamp.backoffice.exception.custom.InvalidPasswordException;
import edu.bootcamp.backoffice.exception.custom.NotCreatedException;
import edu.bootcamp.backoffice.exception.custom.UnauthorizedException;
import edu.bootcamp.backoffice.model.Usuario;
import edu.bootcamp.backoffice.model.dto.LoginDto;
import edu.bootcamp.backoffice.model.dto.UsuarioDto;
import edu.bootcamp.backoffice.repository.UsuarioRepo;

@Service
public class UsuarioService {

	private final UsuarioRepo repo;
	
	public UsuarioService(UsuarioRepo repo) {
		this.repo = repo;
	}

	public Usuario save(UsuarioDto dto) {
		
		if(!checkConsistency(dto)) 
			throw new NotCreatedException("Error al crear el usuario");
		
		return this.repo.save(new Usuario(dto));
		
		//NullPointerException Test
		//String a = null;
		//a.toString();		
		
	}


	public List<UsuarioDto> list() {
		return this.repo
				.findAll()
				.stream()
				.map(Usuario::toDto)
				.collect(Collectors.toList());
	}

	public LoginDto login(LoginDto dto) {
		if(checkConsistency(dto)) {
			Optional<Usuario> user = this.repo.findByEmail(dto.getEmail());
			
			if(user.isPresent()) {
				// valido la contraseña
				if(!user.get().getPassword().equals(dto.getPassword()))
					throw new InvalidPasswordException("Password incorrecta");
					
			} else user.orElseThrow(
				() -> new UnauthorizedException("Credenciales inválidas")
			
			); 
		}
			
		dto.setPassword("*******");
		return dto;
	} 
	

	private boolean checkConsistency(UsuarioDto usuario) {
			
		if(StringUtils.isEmpty(usuario.getEmail())) {
			throw new EmptyElementException("El atributo email no puede estar vacío");
		}
		
		if(StringUtils.isEmpty(usuario.getPassword())) {
			throw new EmptyElementException("El atributo password no puede estar vacío");
		}
						
		if(StringUtils.isEmpty(usuario.getNombre())) {
			throw new EmptyElementException("El atributo nombre no puede estar vacío");
		}
				
		return Boolean.TRUE;
	}

	private boolean checkConsistency(LoginDto login) {
		
		if(StringUtils.isEmpty(login.getEmail())) {
			throw new EmptyElementException("El atributo email no puede estar vacío");
		}
		
		if(StringUtils.isEmpty(login.getPassword())) {
			throw new EmptyElementException("El atributo password no puede estar vacío");
		}

		if((new String("*******")).equals(login.getPassword())) {
			throw new InvalidPasswordException("Password inválida");
		}
		
		return Boolean.TRUE;
	}

}
