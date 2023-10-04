package edu.bootcamp.backoffice;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import edu.bootcamp.backoffice.model.Cliente;
import edu.bootcamp.backoffice.model.Producto;
import edu.bootcamp.backoffice.model.pedido.Pedido;
import edu.bootcamp.backoffice.model.pedido.PedidoItem;
import edu.bootcamp.backoffice.repository.ClienteRepo;
import edu.bootcamp.backoffice.repository.ProductoRepo;
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
		ClienteRepo cliRepo,
		PedidoRepo pedidoRepo,
		PedidoItemRepo pedidoItemRepo
		
	) {
		return args -> { 
			Producto prod1 = prodRepo.save(
										new Producto(null, 
													"celular", 
													BigDecimal.valueOf(5000)													
													/* Si habilito relación entre producto y pedido items , null */ 
													 , null 
													)
										);
			Producto prod2 = prodRepo.save(
										new Producto(null, 
													"modem", 
													BigDecimal.valueOf(3500)
													/* Si habilito relación entre producto y pedido items , null */
													 , null 
													)
										);


			Cliente cli = new Cliente(null, 
					"Perez y Asoc.", 
					"juan@perez.com"
					/* Si habilito relación muchos a muchos a productos , null */, 
					 List.of(prod1, prod2) );												
			
		
			//cli.add(prod1);
			
			cli = cliRepo.save(cli);
									

			
			
			/* crear cabecera del pedido */

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
																BigDecimal.valueOf(2), 
																prod2.getPrecio(), 
																prod2 , null  
																)
												);

			pedido.setItems(Set.of(
								item1,
								item2
			));

			pedidoRepo.save(pedido);
		
		};
	}
 }
