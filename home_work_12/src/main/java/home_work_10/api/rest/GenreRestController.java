package home_work_10.api.rest;

import home_work_10.model.Genre;
import home_work_10.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Genre> getAllBooks() {
        return genreRepo.findAll();
    }

}
