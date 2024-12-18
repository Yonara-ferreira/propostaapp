package com.simulador.proposta.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com/simulador/proposta/app/entity")
public class PropostaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaAppApplication.class, args);
	}

}
