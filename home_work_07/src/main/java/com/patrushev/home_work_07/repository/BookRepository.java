package com.patrushev.home_work_07.repository;

import com.patrushev.home_work_07.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    //@EntityGraph здесь для того, чтобы только 1 запрос шел в базу для доставания сразу из нескольких таблиц
    @EntityGraph(attributePaths = {"author","genre"})
    List<Book> findAll();

    @EntityGraph(attributePaths = {"author","genre"})
    Optional<Book> findById(long id);
}
