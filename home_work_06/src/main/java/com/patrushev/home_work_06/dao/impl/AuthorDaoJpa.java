package com.patrushev.home_work_06.dao.impl;

import com.patrushev.home_work_06.dao.AuthorDao;
import com.patrushev.home_work_06.model.Author;
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
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long insert(Author author) {
        em.persist(author);
        em.flush();
        return author.getId();
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public void update(Author author) {
        Query query = em.createQuery(
                "update Author e set e.name = :name where e.id = :id");
        query.setParameter("id", author.getId());
        query.setParameter("name", author.getName());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Author e where e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.clear();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(e) from Author e",
                Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select e from Author e",
                Author.class);
        return query.getResultList();
    }

}
