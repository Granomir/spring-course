package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.dao.impl.AuthorDaoJpa;
import com.patrushev.home_work_06.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    void insert_ThanGetTheSameAuthorById() {
        Author tolkin = new Author("Толкин");
        long id = authorDao.insert(tolkin);
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
        long id = authorDao.insert(tolstoy);
        long beforeDeletion = authorDao.count();
        authorDao.deleteById(id);
        long afterDeletion = authorDao.count();
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