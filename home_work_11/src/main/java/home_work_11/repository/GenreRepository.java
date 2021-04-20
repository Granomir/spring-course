package home_work_11.repository;

import home_work_11.model.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GenreRepository extends ReactiveMongoRepository<Genre, Long> {
}
