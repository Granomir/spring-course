package com.patrushev.home_work_09.repository;

import com.patrushev.home_work_09.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
