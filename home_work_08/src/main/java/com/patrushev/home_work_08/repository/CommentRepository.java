package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, Long> {
}
