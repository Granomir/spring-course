package com.patrushev.home_work_09.repository;

import com.patrushev.home_work_09.model.Book;
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
