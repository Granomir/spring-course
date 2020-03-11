package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.dao.impl.GenreDaoJpa;
import com.patrushev.home_work_06.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void insert_ThanGetTheSameGenreById() {
        Genre roman = new Genre("Роман");
        long id = genreDao.insert(roman);
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
        long id = genreDao.insert(manga);
        long beforeDeletion = genreDao.count();
        genreDao.deleteById(id);
        long afterDeletion = genreDao.count();
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