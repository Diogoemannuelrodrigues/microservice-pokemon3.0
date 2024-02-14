package br.com.microservice.attack.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"br.com.microservice.attack.application.repository.AttackRepository"})
public class AttackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttackApplication.class, args);
	}

}
