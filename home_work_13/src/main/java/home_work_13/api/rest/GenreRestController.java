package home_work_13.api.rest;

import home_work_13.model.Genre;
import home_work_13.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreRestController {
    private final GenreRepository genreRepo;

    @Autowired
    public GenreRestController(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }

    @GetMapping("/genre")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Genre> getAllBooks() {
        return genreRepo.findAll();
    }

}
