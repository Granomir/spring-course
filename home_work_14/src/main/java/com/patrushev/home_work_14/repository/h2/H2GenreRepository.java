package com.patrushev.home_work_14.repository.h2;

import com.patrushev.home_work_14.model.h2.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2GenreRepository extends JpaRepository<Genre, Long> {
}
