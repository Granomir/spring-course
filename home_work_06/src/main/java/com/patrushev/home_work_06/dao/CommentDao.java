package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.model.Comment;

import java.util.List;

public interface CommentDao {
    long insert(Comment comment);
    Comment getById(long id);
    void update(Comment comment);
    void deleteById(long id);
    long count();
    List<Comment> getAll();
}
