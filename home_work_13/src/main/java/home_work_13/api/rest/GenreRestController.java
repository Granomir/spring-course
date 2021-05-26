package home_work_13.api.rest;

import home_work_13.model.Genre;
import home_work_13.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenreRestController {
    private final GenreRepository genreRepo;

    @GetMapping("/genre")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Genre> getAllBooks() {
        return genreRepo.findAll();
    }

}
