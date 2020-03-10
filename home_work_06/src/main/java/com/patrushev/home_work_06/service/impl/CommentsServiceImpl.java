package com.patrushev.home_work_06.service.impl;

import com.patrushev.home_work_06.dao.CommentDao;
import com.patrushev.home_work_06.model.Comment;
import com.patrushev.home_work_06.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private CommentDao dao;

    @Override
    public void addComment(Comment comment) {
        dao.insert(comment);
    }

    @Override
    public void deleteComment(long id) {
        dao.deleteById(id);
    }

    @Override
    public void editComment(Comment comment) {
        dao.update(comment);
    }

    @Override
    public List<Comment> getComments(long bookId) {
        return dao.getAll();
    }
}
