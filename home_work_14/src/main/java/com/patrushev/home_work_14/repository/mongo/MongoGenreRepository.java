package com.patrushev.home_work_14.repository.mongo;

import com.patrushev.home_work_14.model.mongo.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGenreRepository extends MongoRepository<Genre, Long> {
}
