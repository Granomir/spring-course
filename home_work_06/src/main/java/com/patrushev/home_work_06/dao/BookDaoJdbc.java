package com.patrushev.home_work_06.dao;

import com.patrushev.home_work_06.model.Author;
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
public class BookDaoJdbc implements BookDao {

    @PersistenceContext
    private EntityManager em;

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

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
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        params.addValue("author", book.getAuthor().getId());
        params.addValue("genre", book.getGenre().getId());
        jdbc.update("update books set title = :title, author = :author, genre = :genre where id = :id", params);
    }

    @Override
    public void deleteById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbc.update("delete from books where id = :id", params);
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
