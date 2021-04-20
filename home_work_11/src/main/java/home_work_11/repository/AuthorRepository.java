package home_work_11.repository;

import home_work_11.model.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuthorRepository extends ReactiveMongoRepository<Author, Long> {
}
