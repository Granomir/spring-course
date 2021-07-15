package com.patrushev.home_work_14.repository.mongo;

import com.patrushev.home_work_14.model.mongo.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoAuthorRepository extends MongoRepository<Author, Long> {
}
