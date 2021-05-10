package br.edu.ifce.matheus.pacc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PaccApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaccApplication.class, args);
	}

}
