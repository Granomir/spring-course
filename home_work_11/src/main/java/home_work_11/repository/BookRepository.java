package home_work_11.repository;

import home_work_11.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, Long> {
}
