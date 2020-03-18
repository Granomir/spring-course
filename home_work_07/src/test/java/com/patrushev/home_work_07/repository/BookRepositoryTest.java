package com.patrushev.home_work_07.repository;

import com.patrushev.home_work_07.model.Author;
import com.patrushev.home_work_07.model.Book;
import com.patrushev.home_work_07.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Transactional
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    public void SaveAndFind() {
        Book book = new Book("Война и мир", new Author(1, "Дж. К. Роулинг"), new Genre(1, "Фэнтези"));
        final Book saved = repository.save(book);
        final long savedId = saved.getId();
        assertEquals(8, savedId);
        final Book received = repository.findById(savedId).orElseThrow(EntityNotFoundException::new);
        assertEquals(saved, received);
    }
}
