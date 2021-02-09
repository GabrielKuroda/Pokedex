package com.troca.trocaPokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TrocaPokemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrocaPokemonApplication.class, args);
	}

}
