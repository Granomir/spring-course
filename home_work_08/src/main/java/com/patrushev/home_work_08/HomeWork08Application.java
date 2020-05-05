package com.patrushev.home_work_08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.patrushev.home_work_08.repository")
public class HomeWork08Application {

    //доделать бы когда-нить, но щас нет смысла тратить время, т.к. для этого просто особенности работы с монгой надо изучать:
    // 1) при сохранении сущности должен генериться id - похоже реализуется только созданием еще одной коллекции в БД, в которой хранятся эти id
    // 2) тестовая база должна сбрасываться перед каждым тестом
    public static void main(String[] args) {
        SpringApplication.run(HomeWork08Application.class, args);
    }

}
