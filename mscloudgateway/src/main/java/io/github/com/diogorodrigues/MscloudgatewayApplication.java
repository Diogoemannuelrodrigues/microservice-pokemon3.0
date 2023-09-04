package io.github.com.diogorodrigues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class MscloudgatewayApplication {
	public static final String MSPOKEMON = "lb://mspokemon";

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
					.route(r -> r.path("/api/v1/pokemon").uri(MSPOKEMON))
					.route(r -> r.path("/api/v1/pokemon/{name}").uri(MSPOKEMON))
					.route(r -> r.path("/api/v1/pokemon/all").uri(MSPOKEMON))
					.route(r -> r.path("/api/v1/pokemon/evolutions/{name}").uri(MSPOKEMON))
				.build();
	}

}
