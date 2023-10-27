package edu.bootcamp.backoffice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

	

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BackofficeApplicationTests {

	@Autowired
	MockMvc mock;
	
	@Test
	@Order(0)
	void testUsuariosList() throws Exception {
		mock.perform(get("/user/list")).andDo(print());
		
	}

	
	@Test
	@Order(1)
	void testUsuariosAlta() throws Exception {
		mock.perform(post("/user/crear")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" +
					     "\"email\" : \"adominguez@finnegans.com.ar\"," +
					     "\"password\" : \"pass\"," +
					     "\"nombre\" : \"Antonio Dominguez\"" +
					     "}")
				).andDo(print());
		
	}
	/*
	@Test
	@Order(2)
	void testUsuarioFilter() throws Exception {
		mock.perform(post("/user/crear")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{" +
					     "\"email\" : \"adominguez@finnegans.com.ar\"," +
					     "\"password\" : \"pass\"," +
					     "\"nombre\" : \"Antonio Dominguez\"" +
					     "}")
				).andDo(print());
		
	}
	*/
	
	@Test
	@Order(3)
	void testUsuariosFilteredList() throws Exception {
		mock.perform(get("/user/filtered-list?email=ming")).andDo(print());
		
	}
	
	@Test
	@Order(4)
	void testUsuariosBorrar() throws Exception {
		mock.perform(delete("/user/borrar/1")).andDo(print());
		
	}

}
