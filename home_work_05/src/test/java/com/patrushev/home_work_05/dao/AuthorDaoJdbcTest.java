package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@JdbcTest
@ComponentScan
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

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
        String oldName = author.getName();
        author.setName("Rowling");
        authorDao.update(author);
        Author updatedAuthor = authorDao.getById(1);
        assertEquals(author, updatedAuthor);
        assertNotEquals(oldName, updatedAuthor.getName());
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
        assertEquals(6, authorDao.count());
    }

    @Test
    void getAll() {
        List<Author> all = authorDao.getAll();
        assertEquals(6, all.size());
    }
}