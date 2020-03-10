package com.patrushev.home_work_06.dao.impl;

import com.patrushev.home_work_06.dao.GenreDao;
import com.patrushev.home_work_06.model.Genre;
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
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long insert(Genre genre) {
        em.persist(genre);
        em.flush();
        return genre.getId();
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public void update(Genre genre) {
        Query query = em.createQuery(
                "update Genre e set e.name = :name where e.id = :id");
        query.setParameter("id", genre.getId());
        query.setParameter("name", genre.getName());
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Genre e where e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.clear();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "select count(e) from Genre e",
                Long.class);
        return query.getSingleResult();
    }

    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select e from Genre e",
                Genre.class);
        return query.getResultList();
    }

}
