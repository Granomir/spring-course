package com.patrushev.home_work_09.repository;

import com.patrushev.home_work_09.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
