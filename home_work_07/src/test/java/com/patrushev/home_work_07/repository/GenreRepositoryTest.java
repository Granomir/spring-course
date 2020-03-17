package com.patrushev.home_work_07.repository;

import com.patrushev.home_work_07.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository repository;

    @Test
    public void SaveAndFind() {
        Genre roman = new Genre("Роман");
        final Genre saved = repository.save(roman);
        final long savedId = saved.getId();
        assertEquals(5, savedId);
        final Genre received = repository.findById(savedId).orElseThrow(EntityNotFoundException::new);
        assertEquals(saved, received);
    }
}
