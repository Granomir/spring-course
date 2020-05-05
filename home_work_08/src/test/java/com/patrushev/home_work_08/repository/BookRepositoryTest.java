package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.exception.NotFoundException;
import com.patrushev.home_work_08.model.Author;
import com.patrushev.home_work_08.model.Book;
import com.patrushev.home_work_08.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void SaveAndFind() {
        Book book = new Book("Война и мир", new Author(1, "Дж. К. Роулинг"), new Genre(1, "Фэнтези"));
        final Book saved = repository.save(book);
        final long savedId = saved.getId();
        assertEquals(8, savedId);
        assertEquals(6, authorRepository.count());
        final Book received = repository.findById(savedId).orElseThrow(NotFoundException::new);
        assertEquals(saved, received);
    }
}
