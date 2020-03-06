package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.model.Book;
import com.patrushev.home_work_06.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

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
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", genre.getId());
        params.addValue("name", genre.getName());
        jdbc.update("update genres set name = :name where id = :id", params);
    }

    @Override
    public void deleteById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbc.update("delete from genres where id = :id", params);
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
