package com.patrushev.home_work_06.dao.impl;

import com.patrushev.home_work_06.dao.CommentDao;
import com.patrushev.home_work_06.model.Book;
import com.patrushev.home_work_06.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long insert(Comment comment) {
        em.persist(comment);
        em.flush();
        return comment.getId();
    }

    @Override
    public Comment getById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void update(Comment comment) {
        Query query = em.createQuery(
                "update Comment e set e.nickName = :nickName, e.body = :body, e.bookId = :bookId where e.id = :id");
        query.setParameter("id", comment.getId());
        query.setParameter("nickName", comment.getNickName());
        query.setParameter("body", comment.getBody());
        query.setParameter("bookId", comment.getBookId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Comment e where e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.clear();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(e) from Comment e",
                Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery(
                "select e from Comment e",
                Comment.class);
        return query.getResultList();
    }
}
