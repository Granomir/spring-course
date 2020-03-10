package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.model.Book;
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
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long insert(Book book) {
        em.persist(book);
        em.flush();
        return book.getId();
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public void update(Book book) {
        Query query = em.createQuery(
                "update Book e set e.title = :title, e.author = :author, e.genre = :genre where e.id = :id");
        query.setParameter("id", book.getId());
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());
        query.setParameter("genre", book.getGenre());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Book e where e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.clear();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(e) from Book e",
                Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(
                "select e from Book e",
                Book.class);
        return query.getResultList();
    }

}
