package com.patrushev.home_work_07.repository;

import com.patrushev.home_work_07.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
