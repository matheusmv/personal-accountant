package br.edu.ifce.matheus.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "br.edu.ifce.matheus.**")
@EnableMongoRepositories("br.edu.ifce.matheus.mongo")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}