package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.model.Genre;
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
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int insert(Genre genre) {
        em.persist(genre);
        em.flush();
        return genre.getId();
    }

    @Override
    public Genre getById(int id) {
        return em.find(Genre.class, id);
    }

    @Override
    public void update(Genre genre) {
        TypedQuery<Genre> query = em.createQuery(
                "update Genre e set e.name = :name where e.id = :id",
                Genre.class);
        query.setParameter("id", genre.getId());
        query.setParameter("name", genre.getName());
        query.executeUpdate();
    }

    @Override
    public void deleteById(int id) {
        TypedQuery<Genre> query = em.createQuery(
                "delete from Genre e where e.id = :id",
                Genre.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public int count() {
        TypedQuery<Integer> query = em.createQuery(
                "select count(e) from Genre e",
                Integer.class);
        return query.getSingleResult();
    }

    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select e from Genre e",
                Genre.class);
        return query.getResultList();
    }

}
