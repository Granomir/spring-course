package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.exception.NotFoundException;
import com.patrushev.home_work_08.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository repository;

    @Test
    public void SaveAndFind() {
        Genre roman = new Genre("Роман");
        final Genre saved = repository.save(roman);
        final long savedId = saved.getId();
        System.out.println(savedId);
        assertEquals(5, savedId);
        final Genre received = repository.findById(savedId).orElseThrow(NotFoundException::new);
        assertEquals(saved, received);
    }
}
