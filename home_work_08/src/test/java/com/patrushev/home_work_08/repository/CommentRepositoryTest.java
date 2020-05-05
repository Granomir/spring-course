package com.patrushev.home_work_08.repository;

import com.patrushev.home_work_08.exception.NotFoundException;
import com.patrushev.home_work_08.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository repository;

    @Test
    public void SaveAndFind() {
        Comment comment = new Comment(0, "Roman", "Cool story!", 1);
        final Comment saved = repository.save(comment);
        final long savedId = saved.getId();
        assertEquals(1, savedId);
        final Comment received = repository.findById(savedId).orElseThrow(NotFoundException::new);
        assertEquals(saved, received);
    }
}
