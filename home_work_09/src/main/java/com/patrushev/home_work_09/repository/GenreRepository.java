package com.patrushev.home_work_09.repository;

import com.patrushev.home_work_09.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
