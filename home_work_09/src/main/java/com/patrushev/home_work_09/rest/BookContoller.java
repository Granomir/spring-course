package com.patrushev.home_work_09.rest;

import com.patrushev.home_work_09.model.Book;
import com.patrushev.home_work_09.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
