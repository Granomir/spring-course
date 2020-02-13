package com.patrushev.home_work_05;

import com.patrushev.home_work_05.dao.AuthorDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HomeWork05Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HomeWork05Application.class, args);
        AuthorDao dao = context.getBean(AuthorDao.class);
        System.out.printf("В таблице authors %d записей%n", dao.count());
    }

}
