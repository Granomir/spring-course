package home_work_11.api.rest;

import home_work_11.exception.NotFoundException;
import home_work_11.model.Book;
import home_work_11.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    private final BookRepository bookRepo;

    @Autowired
    public BookRestController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/book/count")
    public long getBooksCount() {
        return bookRepo.count();
    }

    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book) {
        book.setId((int) (Math.random() * (999999 - 1)) + 1);
        return bookRepo.save(book);
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable long id) {
        bookRepo.deleteById(id);
    }

}
