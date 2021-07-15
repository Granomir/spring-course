package com.patrushev.home_work_14.repository.mongo;

import com.patrushev.home_work_14.model.mongo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoBookRepository extends MongoRepository<Book, Long> {
}
