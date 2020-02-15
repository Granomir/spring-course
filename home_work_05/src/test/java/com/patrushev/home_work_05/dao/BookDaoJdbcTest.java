package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import com.patrushev.home_work_05.model.Book;
import com.patrushev.home_work_05.model.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookDaoJdbcTest {
    @Autowired
    private BookDao bookDao;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {
        Book book = new Book(0, "Война и мир", new Author(1, "Дж. К. Роулинг"), new Genre(1, "Фэнтези"));
        int id = bookDao.insert(book);
        book.setId(id);
        Book sameBook = bookDao.getById(id);
        assertEquals(book, sameBook);
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