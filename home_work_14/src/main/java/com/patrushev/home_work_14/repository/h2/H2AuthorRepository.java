package com.patrushev.home_work_14.repository.h2;

import com.patrushev.home_work_14.model.h2.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2AuthorRepository extends JpaRepository<Author, Long> {
}
