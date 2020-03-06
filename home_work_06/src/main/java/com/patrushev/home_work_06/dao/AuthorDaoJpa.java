package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.model.Author;
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
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int insert(Author author) {
        em.persist(author);
        em.flush();
        return author.getId();
    }

    @Override
    public Author getById(int id) {
        return em.find(Author.class, id);
    }

    @Override
    public void update(Author author) {
        TypedQuery<Author> query = em.createQuery(
                "update Author e set e.name = :name where e.id = :id",
                Author.class);
        query.setParameter("id", author.getId());
        query.setParameter("name", author.getName());
        query.executeUpdate();
    }

    @Override
    public void deleteById(int id) {
        TypedQuery<Author> query = em.createQuery(
                "delete from Author e where e.id = :id",
                Author.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public int count() {
        TypedQuery<Integer> query = em.createQuery(
                "select count(e) from Author e",
                Integer.class);
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
