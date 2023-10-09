package edu.bootcamp.backoffice;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import edu.bootcamp.backoffice.model.bienes.Producto;
import edu.bootcamp.backoffice.model.bienes.Servicio;
import edu.bootcamp.backoffice.model.cliente.Cliente;
import edu.bootcamp.backoffice.model.cliente.Empresa;
import edu.bootcamp.backoffice.model.cliente.EmpresaCategoria;
import edu.bootcamp.backoffice.model.cliente.Persona;
import edu.bootcamp.backoffice.model.pedido.Pedido;
import edu.bootcamp.backoffice.model.pedido.PedidoItem;
import edu.bootcamp.backoffice.repository.ClienteRepo;
import edu.bootcamp.backoffice.repository.ProductoRepo;
import edu.bootcamp.backoffice.repository.ServicioRepo;
import edu.bootcamp.backoffice.repository.pedido.PedidoItemRepo;
import edu.bootcamp.backoffice.repository.pedido.PedidoRepo;

@SpringBootApplication
public class BackofficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackofficeApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Arrays.asList("*"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	CommandLineRunner commandLineRunner(
		ProductoRepo prodRepo,
		ServicioRepo servRepo,
		ClienteRepo cliRepo,
		PedidoRepo pedidoRepo,
		PedidoItemRepo pedidoItemRepo
		
	) {
		return args -> { 
			
			Producto prod1 = prodRepo.save(
										new Producto(null, 
													"celular", 
													BigDecimal.valueOf(5000),
													BigDecimal.valueOf(300.2)
													/* Si habilito relación entre producto y pedido items , null */ 
													/* , null */
													)
										);
			Producto prod2 = prodRepo.save(
										new Producto(null, 
													"modem", 
													BigDecimal.valueOf(3500),
													BigDecimal.ZERO

													/* Si habilito relación entre producto y pedido items , null */
													/* , null */
													)
										);

			Servicio serv = servRepo.save(
										new Servicio(null, 
													"televisión por cable", 
													BigDecimal.valueOf(11000),
													BigDecimal.valueOf(300.2)
													/* Si habilito relación entre producto y pedido items , null */ 
													/* , null */
													)
										);			
			
			Cliente cli;
			
			cli = new Persona(null, 
					"Juan García", 
					"jgarcia@mail.com"
					/* Si habilito relación muchos a muchos a productos , null */
					/*,  List.of(prod1, prod2) */ );												
			
		
			//cli.add(prod1);
			
			cli = cliRepo.save(cli);
									

			cli = new Empresa(null, 
					"Perez y Asoc.", 
					new Date(),
					EmpresaCategoria.RESPONSABLE_INSCRIPTO
					/* Si habilito relación muchos a muchos a productos , null */
					/*,  List.of(prod1, prod2) */ );	
			
			cli = cliRepo.save(cli);
			
			/* =========== PEDIDO ============ */
			
			Optional instance = cliRepo.findById(1);
			
			cli = (Cliente)instance.get();
			
			System.out.println("Nombre : " + cli.getNombre() );
			
			Pedido pedido = pedidoRepo.save(
										new Pedido(null, 
													"",
													Integer.valueOf(1),
													cli  , null  
													)
										);
 
			PedidoItem item1 = pedidoItemRepo.save(
												new PedidoItem(null, 
																BigDecimal.valueOf(4), 
																prod1.getPrecio(), 
																prod1 , null 
																)
												);

			PedidoItem item2 = pedidoItemRepo.save(
												new PedidoItem(null, 
																BigDecimal.valueOf(1), 
																prod2.getPrecio(), 
																prod2 , null  
																)
												);			
			
			PedidoItem item3 = pedidoItemRepo.save(
												new PedidoItem(null, 
																null, 
																serv.getPrecio(), 
																serv , null  
																)
												);

			pedido.setItems(Set.of(
								item1,
								item2,
								item3
			));

			pedidoRepo.save(pedido);
		
			
			Optional<Pedido> transaccion = pedidoRepo.findById(1);
			
			pedido = (Pedido)transaccion.get();
			
			Set items = pedido.getItems();
			for(Iterator it = items.iterator(); it.hasNext();) {
				PedidoItem item = (PedidoItem)it.next();
				System.out.println("------------------");
				//System.out.println("Item: " + item.getOfrecido().getNombre() );
			}
		
		};
	}
 }
