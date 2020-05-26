package com.patrushev.home_work_09.repository;

import com.patrushev.home_work_09.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
