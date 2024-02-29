package io.github.msbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBatchApplication.class, args);
	}

}
