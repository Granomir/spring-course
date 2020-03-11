package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.dao.impl.BookDaoJpa;
import com.patrushev.home_work_06.model.Author;
import com.patrushev.home_work_06.model.Book;
import com.patrushev.home_work_06.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void insert_ThanGetTheSameBookById() {
        Book book = new Book("Война и мир", new Author(1, "Дж. К. Роулинг"), new Genre(1, "Фэнтези"));
        long id = bookDao.insert(book);
        book.setId(id);
        Book sameBook = bookDao.getById(id);
        assertEquals(book, sameBook);
    }

    @Test
    void update_ThanGetTheSameUpdatedBookById() {
        Book book = bookDao.getById(1);
        String oldTitle = book.getTitle();
        Author oldAuthor = book.getAuthor();
        Genre oldGenre = book.getGenre();
        book.setTitle("Варкрафт");
        book.setAuthor(new Author(2, "Стивен Кинг"));
        book.setGenre(new Genre(2, "Мистика"));
        bookDao.update(book);
        Book updatedBook = bookDao.getById(1);
        assertEquals(book, updatedBook);
        assertNotEquals(oldTitle, updatedBook.getTitle());
        assertNotEquals(oldAuthor, updatedBook.getAuthor());
        assertNotEquals(oldGenre, updatedBook.getGenre());
    }

    @Test
    void insertBook_ThanDelete_ThanGettingBookByIdNotExistReturnNull() {
        Book warcraft2 = new Book("Варкрафт 2", new Author(2, "Стивен Кинг"), new Genre(2, "Мистика"));
        long id = bookDao.insert(warcraft2);
        long beforeDeletion = bookDao.count();
        bookDao.deleteById(id);
        long afterDeletion = bookDao.count();
        assertEquals(1, beforeDeletion - afterDeletion);
        assertNull(bookDao.getById(id));
    }

    @Test
    void count() {
        assertEquals(7, bookDao.count());
    }

    @Test
    void getAll() {
        List<Book> all = bookDao.getAll();
        assertEquals(7, all.size());
    }
}