package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@ContextConfiguration(classes = GenreDaoJdbc.class)
@ExtendWith(SpringExtension.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void insert_ThanGetTheSameGenreById() {
        Genre roman = new Genre("Роман");
        int id = genreDao.insert(roman);
        roman.setId(id);
        Genre mustBeRoman = genreDao.getById(id);
        assertEquals(roman, mustBeRoman);
    }

    @Test
    void update_ThanGetTheSameUpdatedGenreById() {
        Genre genre = genreDao.getById(1);
        genre.setName("Драма");
        genreDao.update(genre);
        Genre updatedGenre = genreDao.getById(1);
        assertEquals(genre, updatedGenre);
    }

    @Test
    void insertGenre_ThanDelete_ThanGettingGenreByIdNotExistReturnNull() {
        Genre manga = new Genre("Манга");
        int id = genreDao.insert(manga);
        int beforeDeletion = genreDao.count();
        genreDao.deleteById(id);
        int afterDeletion = genreDao.count();
        assertEquals(1, beforeDeletion - afterDeletion);
        assertNull(genreDao.getById(id));
    }

    @Test
    void count() {
        assertEquals(4, genreDao.count());
    }

    @Test
    void getAll() {
        List<Genre> all = genreDao.getAll();
        assertEquals(4, all.size());
    }
}