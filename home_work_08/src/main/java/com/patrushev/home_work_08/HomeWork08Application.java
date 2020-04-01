package com.patrushev.home_work_08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.patrushev.home_work_08.repository")
public class HomeWork08Application {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork08Application.class, args);
    }

}
