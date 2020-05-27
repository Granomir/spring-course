package com.patrushev.home_work_09.rest;

import com.patrushev.home_work_09.model.Book;
import com.patrushev.home_work_09.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class BookContoller {

    private final BookRepository repo;

    @Autowired
    public BookContoller(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        final List<Book> books = repo.findAll();
        model.addAttribute("books", books);
        final long count = repo.count();
        model.addAttribute("booksCount", count);
        return "main";
    }

    @GetMapping("/add")
    public String addBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    //TODO проверить создаются ли еще раз авторы/жанры при добавлении книги с уже существующими в БД авторами/жанрами
    // а еще создаются ли книги, если одинаковое название книги ввести (вообще наверно всё это создается, потому что id же другой у них будет)
    // TODO по хорошему надо проверять наверно как-то что такие уже существуют и не инсертить лишний раз
    @PostMapping("/book/add")
    public RedirectView addBook(@ModelAttribute Book book) {
        repo.save(book);
        return new RedirectView("/");
    }

    @PostMapping("/book/delete")
    public RedirectView deleteBook(@RequestParam long id) {
        repo.deleteById(id);
        return new RedirectView("/");
    }

}
