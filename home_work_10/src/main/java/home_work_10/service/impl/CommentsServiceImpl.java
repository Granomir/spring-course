package home_work_10.service.impl;

import home_work_10.model.Comment;
import home_work_10.repository.CommentRepository;
import home_work_10.service.CommentsService;
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
