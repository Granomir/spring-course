package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.dao.impl.CommentDaoJpa;
import com.patrushev.home_work_06.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(CommentDaoJpa.class)
class CommentDaoJpaTest {

    @Autowired
    private CommentDao commentDao;

    @Test
    void insert_ThanGetTheSameCommentById() {
        Comment comment = new Comment(0, "Roman", "Cool story!", 1);
        long id = commentDao.insert(comment);
        comment.setId(id);
        Comment sameComment = commentDao.getById(id);
        assertEquals(comment, sameComment);
    }

    @Test
    void update_ThanGetTheSameUpdatedBookById() {
        Comment firstComment = new Comment(0, "Roman", "Cool story!", 1);
        long id = commentDao.insert(firstComment);
        Comment comment = commentDao.getById(id);
        String oldBody = comment.getBody();
        comment.setBody("VERY BORING!");
        commentDao.update(comment);
        Comment updatedComment = commentDao.getById(id);
        assertEquals(comment, updatedComment);
        assertNotEquals(oldBody, updatedComment.getBody());
    }

    @Test
    void insertBook_ThanDelete_ThanGettingBookByIdNotExistReturnNull() {
        Comment comment = new Comment(0, "Roman", "Cool story!", 1);
        long id = commentDao.insert(comment);
        long beforeDeletion = commentDao.count();
        commentDao.deleteById(id);
        long afterDeletion = commentDao.count();
        assertEquals(1, beforeDeletion - afterDeletion);
        assertNull(commentDao.getById(id));
    }

    @Test
    void count() {
        Comment comment = new Comment(0, "Roman", "Cool story!", 1);
        commentDao.insert(comment);
        assertEquals(1, commentDao.count());
    }

    @Test
    void getAll() {
        Comment comment = new Comment(0, "Roman", "Cool story!", 1);
        commentDao.insert(comment);
        List<Comment> all = commentDao.getAll();
        assertEquals(1, all.size());
    }
}