package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {
        Author tolkin = new Author("Толкин");
        final int id = authorDao.insert(tolkin);
        tolkin.setId(id);
        final Author mustBeTolkin = authorDao.getById(id);
        assertEquals(tolkin, mustBeTolkin);
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void count() {
    }
}