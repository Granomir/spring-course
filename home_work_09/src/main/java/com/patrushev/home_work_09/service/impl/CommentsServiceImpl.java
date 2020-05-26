package com.patrushev.home_work_09.service.impl;

import com.patrushev.home_work_09.model.Comment;
import com.patrushev.home_work_09.repository.CommentRepository;
import com.patrushev.home_work_09.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final CommentRepository repository;

    @Override
    public void addComment(Comment comment) {
        repository.save(comment);
    }

    @Override
    public void deleteComment(long id) {
        repository.deleteById(id);
    }

    @Override
    public void editComment(long id, String body) {
        Comment comment = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        comment.setBody(body);
        repository.save(comment);
    }

    @Override
    public List<Comment> getComments(long bookId) {
        return repository.findAll();
    }
}
