package home_work_11.api.rest;

import home_work_11.exception.NotFoundException;
import home_work_11.model.Book;
import home_work_11.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookRestController {

    private final BookRepository bookRepo;

    @Autowired
    public BookRestController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/book")
    public Flux<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @GetMapping("/book/{id}")
    public Mono<Book> getBookById(@PathVariable long id) {
        return bookRepo.findById(id).switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @GetMapping("/book/count")
    public Mono<Long> getBooksCount() {
        return bookRepo.count();
    }

    @PostMapping("/book")
    public Mono<Book> saveBook(@RequestBody Book book) {
        book.setId((int) (Math.random() * (999999 - 1)) + 1);
        return bookRepo.save(book);
    }

    @PutMapping("/book")
    public Mono<Book> updateBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable long id) {
        bookRepo.deleteById(id);
    }

}
