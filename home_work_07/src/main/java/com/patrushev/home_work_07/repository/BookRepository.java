package com.patrushev.home_work_07.repository;

import com.patrushev.home_work_07.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
