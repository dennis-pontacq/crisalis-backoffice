package edu.bootcamp.backoffice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.bootcamp.backoffice.model.Usuario;
import edu.bootcamp.backoffice.model.dto.LoginDto;
import edu.bootcamp.backoffice.model.dto.UsuarioDto;
import edu.bootcamp.backoffice.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UsuarioController {

	@Autowired
	private final UsuarioService service;

	public UsuarioController(UsuarioService service) {
		this.service = service;
	};
	
	@PostMapping(value="crear", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario save(@RequestBody UsuarioDto dto) {
		System.out.println(dto);

		System.out.println("mi modificacion");
		return this.service.save(dto);
	}

	@DeleteMapping(value="borrar/{id}")
	public void delete(@PathVariable("id") int id) {
		//System.out.println(dto);
		//System.out.println("borrando: " + id);
		this.service.remove(id);
	}
	
	@GetMapping(value="login", produces=MediaType.APPLICATION_JSON_VALUE)
	/*@GetMapping(value="login", produces=MediaType.APPLICATION_XML_VALUE) */
	public LoginDto login(@RequestParam String email, String password) {
		return this.service.login(new LoginDto(email, password));
	}
	
		
	@GetMapping(value="list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuarioDto> list() {
		return this.service.list();
	}

	@GetMapping(value="filtered-list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuarioDto> list(@RequestParam String email) {
		return this.service.filter(email);
	}

}
 