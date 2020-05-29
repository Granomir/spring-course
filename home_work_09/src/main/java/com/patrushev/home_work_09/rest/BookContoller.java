package com.patrushev.home_work_09.rest;

import com.patrushev.home_work_09.model.Book;
import com.patrushev.home_work_09.repository.AuthorRepository;
import com.patrushev.home_work_09.repository.BookRepository;
import com.patrushev.home_work_09.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class BookContoller {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final GenreRepository genreRepo;

    @Autowired
    public BookContoller(BookRepository bookRepo, AuthorRepository authorRepo, GenreRepository genreRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        final List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        final long count = bookRepo.count();
        model.addAttribute("booksCount", count);
        return "main";
    }

    @GetMapping("/add")
    public String addBookPage(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("genres", genreRepo.findAll());
        return "addBook";
    }

    //TODO проверить создаются ли еще раз авторы/жанры при добавлении книги с уже существующими в БД авторами/жанрами
    // а еще создаются ли книги, если одинаковое название книги ввести (вообще наверно всё это создается, потому что id же другой у них будет)
    // TODO по хорошему надо проверять наверно как-то что такие уже существуют и не инсертить лишний раз
    @PostMapping("/book/add")
    public RedirectView addBook(@ModelAttribute Book book) {
        bookRepo.save(book);
        return new RedirectView("/");
    }

    @PostMapping("/book/delete")
    public RedirectView deleteBook(@RequestParam long id) {
        bookRepo.deleteById(id);
        return new RedirectView("/");
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam long id, Model model) {
        final Book book = bookRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("genres", genreRepo.findAll());
        return "editBook";
    }

    @PostMapping("/book/update")
    public RedirectView updateBook(@ModelAttribute Book book) {
        System.out.println("updating book: " + book);
        bookRepo.save(book);
        return new RedirectView("/");
    }

    //TODO с ALL не работает удаление, а с PERSIST не работает update

}
