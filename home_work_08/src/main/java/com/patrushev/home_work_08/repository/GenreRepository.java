package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, Long> {
}
