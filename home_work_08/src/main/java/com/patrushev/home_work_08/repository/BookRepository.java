package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Long> {
}
