package com.patrushev.home_work_07.repository;

import com.patrushev.home_work_07.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
