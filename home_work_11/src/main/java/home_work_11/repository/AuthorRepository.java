package home_work_11.repository;

import home_work_11.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, Long> {
}
