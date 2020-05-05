package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.exception.NotFoundException;
import com.patrushev.home_work_08.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@DirtiesContext
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    public void SaveAndFind() {
        Author tolkin = new Author("Толкин");
        final Author saved = repository.save(tolkin);
        final long savedId = saved.getId();
        assertEquals(1, repository.count());
        final Author received = repository.findById(savedId).orElseThrow(NotFoundException::new);
        assertEquals(saved, received);
    }

    @Test
    public void SaveAndFind2() {
        Author tolkin = new Author("Толкин");
        final Author saved = repository.save(tolkin);
        final long savedId = saved.getId();
        assertEquals(1, repository.count());
        final Author received = repository.findById(savedId).orElseThrow(NotFoundException::new);
        assertEquals(saved, received);
    }
}
