package ufcg.edu.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "ufcg.edu.br.models")
@EnableJpaRepositories(basePackages = "ufcg.edu.br.repositories")
@SpringBootApplication(scanBasePackages = "ufcg.edu.br")
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}
}
