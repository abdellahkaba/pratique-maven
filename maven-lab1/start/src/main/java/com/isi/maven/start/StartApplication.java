package com.isi.maven.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.isi.maven")
@ComponentScan(basePackages = {"com.isi.maven.services", "com.isi.maven.web"})
@EnableJpaRepositories(basePackages = "com.isi.maven.services.repository")
@EntityScan(basePackages = "com.isi.maven.services.model")
@ComponentScan(basePackages = "com.isi.maven.services.exception")

public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}