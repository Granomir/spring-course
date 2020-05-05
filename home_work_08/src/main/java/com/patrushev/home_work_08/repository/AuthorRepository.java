package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, Long> {
}
