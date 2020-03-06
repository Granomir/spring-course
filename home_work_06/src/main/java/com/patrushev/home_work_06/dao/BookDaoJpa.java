package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int insert(Book book) {
        em.persist(book);
        em.flush();
        return book.getId();
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public void update(Book book) {
        TypedQuery<Book> query = em.createQuery(
                "update Book e set e.title = :title, e.author = :author, e.genre = :genre where e.id = :id",
                Book.class);
        query.setParameter("id", book.getId());
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor().getId());
        query.setParameter("genre", book.getGenre().getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(int id) {
        TypedQuery<Book> query = em.createQuery(
                "delete from Book e where e.id = :id",
                Book.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public int count() {
        TypedQuery<Integer> query = em.createQuery(
                "select count(e) from Book e",
                Integer.class);
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
