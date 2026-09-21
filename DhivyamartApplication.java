package com.dhivya.dhivyamart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dhivya.dhivyamart")
public class DhivyamartApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhivyamartApplication.class, args);
    }
}