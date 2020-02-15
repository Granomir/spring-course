package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//@DataJpaTest
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthorDaoJdbcTest {
    //TODO разобраться с тестированием репозиториев и чтобы в каждом методе БД инициализировалась по новой

    @Autowired
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert_ThanGetTheSameAuthorById() {
        Author tolkin = new Author("Толкин");
        int id = authorDao.insert(tolkin);
        tolkin.setId(id);
        Author mustBeTolkin = authorDao.getById(id);
        assertEquals(tolkin, mustBeTolkin);
    }

    @Test
    void update_ThanGetTheSameUpdatedAuthorById() {
        Author author = authorDao.getById(1);
        author.setName("Rowling");
        authorDao.update(author);
        Author updatedAuthor = authorDao.getById(1);
        assertEquals(author, updatedAuthor);
    }

    @Test
    void insertAuthor_ThanDelete_ThanGettingAuthorByIdNotExistReturnNull() {
        Author tolstoy = new Author("Толстой");
        int id = authorDao.insert(tolstoy);
        int beforeDeletion = authorDao.count();
        authorDao.deleteById(id);
        int afterDeletion = authorDao.count();
        assertEquals(1, beforeDeletion - afterDeletion);
        assertNull(authorDao.getById(id));
    }

    @Test
    void count() {
    }

    @Test
    void getAll() {

    }
}