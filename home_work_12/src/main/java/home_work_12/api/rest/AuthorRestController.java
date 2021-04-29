package home_work_12.api.rest;

import home_work_12.model.Author;
import home_work_12.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
