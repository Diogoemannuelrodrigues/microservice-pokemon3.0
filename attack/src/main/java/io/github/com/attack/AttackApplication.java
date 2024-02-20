package io.github.com.attack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AttackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttackApplication.class, args);
	}
}
