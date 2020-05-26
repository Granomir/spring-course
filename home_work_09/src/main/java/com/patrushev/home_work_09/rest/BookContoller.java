package com.patrushev.home_work_09.rest;

import com.patrushev.home_work_09.model.Book;
import com.patrushev.home_work_09.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

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

    @PostMapping("/add")
    public RedirectView addBook(@ModelAttribute Book book) {
        repo.save(book);
        return new RedirectView("/");
    }

}
