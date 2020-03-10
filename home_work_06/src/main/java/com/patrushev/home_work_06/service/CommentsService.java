package com.patrushev.home_work_06.service;

import com.patrushev.home_work_06.model.Comment;

import java.util.List;

public interface CommentsService {
    void addComment(Comment comment);
    void deleteComment(long id);
    void editComment(Comment comment);
    List<Comment> getComments(long bookId);
}
