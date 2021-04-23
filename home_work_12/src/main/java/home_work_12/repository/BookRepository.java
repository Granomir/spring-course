package home_work_12.repository;

import home_work_12.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"author","genre"})
    List<Book> findAll();

    @EntityGraph(attributePaths = {"author","genre"})
    Optional<Book> findById(long id);
}
