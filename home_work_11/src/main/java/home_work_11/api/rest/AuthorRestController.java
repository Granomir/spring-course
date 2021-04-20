package home_work_11.api.rest;

import home_work_11.model.Author;
import home_work_11.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorRestController {

    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorRestController(AuthorRepository AuthorRepo) {
        this.authorRepo = AuthorRepo;
    }

    @GetMapping("/author")
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @PostMapping("/author")
    public Author saveAuthor(@RequestBody Author author) {
        return authorRepo.save(author);
    }

}
