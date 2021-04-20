package home_work_11.repository;

import home_work_11.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, Long> {
}
