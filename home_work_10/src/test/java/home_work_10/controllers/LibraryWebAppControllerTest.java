package home_work_10.controllers;

import home_work_10.model.Author;
import home_work_10.model.Book;
import home_work_10.model.Genre;
import home_work_10.repository.BookRepository;
import home_work_10.api.LibraryWebAppController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibraryWebAppController.class)
public class LibraryWebAppControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepo;

    @Test
    public void testMainPage() throws Exception {
        final List<Book> books = Collections.singletonList(new Book(1, "War and Peace", new Author(1, "Tolstoy"), new Genre(1, "Roman")));
        given(bookRepo.findAll())
                .willReturn(books);
        given(bookRepo.count()).willReturn(Long.valueOf(books.size()));
        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", books))
                .andExpect(model().attribute("booksCount", (long) books.size()))
                .andExpect(view().name("mainBook"));
    }

    @Test
    public void testAddBookPage() throws Exception {
        mvc.perform(post("/book/add").content("title=Google&author=1&genre=1"));
        verify(bookRepo, times(1)).save(any(Book.class));
    }

}
