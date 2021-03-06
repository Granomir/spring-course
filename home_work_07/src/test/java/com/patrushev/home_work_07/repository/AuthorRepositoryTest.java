package com.patrushev.home_work_07.repository;

import com.patrushev.home_work_07.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository repository;

    @Test
    public void SaveAndFind() {
        Author tolkin = new Author("Толкин");
        final Author saved = repository.save(tolkin);
        final long savedId = saved.getId();
        assertEquals(7, repository.count());
        final Author received = repository.findById(savedId).orElseThrow(EntityNotFoundException::new);
        assertEquals(saved, received);
    }
}
