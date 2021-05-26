package home_work_13.api.rest;

import home_work_13.model.Book;
import home_work_13.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookRestController {
    private final BookRepository bookRepo;

    @GetMapping("/book")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @GetMapping("/book/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Book getBookById(@PathVariable long id) {
        return bookRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("/book/count")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public long getBooksCount() {
        return bookRepo.count();
    }

    @PostMapping("/book")
    @Secured({"ROLE_ADMIN"})
    public Book saveBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @PutMapping("/book")
    @Secured({"ROLE_ADMIN"})
    public Book updateBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @DeleteMapping("/book/{id}")
    @Secured({"ROLE_ADMIN"})
    public void deleteBookById(@PathVariable long id) {
        bookRepo.deleteById(id);
    }

}
