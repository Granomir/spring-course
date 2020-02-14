package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDaoJdbcTest {

    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {
        authorDao.insert(new Author(1, "Толкин"));
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