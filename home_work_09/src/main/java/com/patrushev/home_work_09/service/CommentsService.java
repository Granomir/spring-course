package com.patrushev.home_work_09.service;

import com.patrushev.home_work_09.model.Comment;

import java.util.List;

public interface CommentsService {
    void addComment(Comment comment);
    void deleteComment(long id);
    void editComment(long id, String body);
    List<Comment> getComments(long bookId);
}
