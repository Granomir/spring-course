package home_work_10.rest;

import home_work_10.model.Book;
import home_work_10.repository.AuthorRepository;
import home_work_10.repository.BookRepository;
import home_work_10.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final GenreRepository genreRepo;

    @Autowired
    public BookController(BookRepository bookRepo, AuthorRepository authorRepo, GenreRepository genreRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
    }

    @GetMapping("/book")
    public String mainPage(Model model) {
        final List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        final long count = bookRepo.count();
        model.addAttribute("booksCount", count);
        return "mainBook";
    }

    @GetMapping("/book/add")
    public String addBookPage(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("genres", genreRepo.findAll());
        return "addBook";
    }

    @PostMapping("/book/add")
    public RedirectView addBook(Book book) {
        bookRepo.save(book);
        return new RedirectView("/book");
    }

    @PostMapping("/book/delete")
    public RedirectView deleteBook(@RequestParam long id) {
        bookRepo.deleteById(id);
        return new RedirectView("/book");
    }

    @GetMapping("/book/edit")
    public String editBookPage(@RequestParam long id, Model model) {
        final Book book = bookRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("genres", genreRepo.findAll());
        return "editBook";
    }

    @PostMapping("/book/update")
    public RedirectView updateBook(Book book) {
        System.out.println("updating book: " + book);
        bookRepo.save(book);
        return new RedirectView("/book");
    }

}
