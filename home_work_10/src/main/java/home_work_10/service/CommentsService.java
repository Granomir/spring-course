package home_work_10.service;

import home_work_10.model.Comment;

import java.util.List;

public interface CommentsService {
    void addComment(Comment comment);
    void deleteComment(long id);
    void editComment(long id, String body);
    List<Comment> getComments(long bookId);
}
