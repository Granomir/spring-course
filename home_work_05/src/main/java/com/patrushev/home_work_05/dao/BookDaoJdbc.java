package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import com.patrushev.home_work_05.model.Book;
import com.patrushev.home_work_05.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public int insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author", book.getAuthor().getId());
        params.addValue("genre", book.getGenre().getId());
        jdbc.update("insert into books (title, author, genre) values (:title, :author, :genre)", params, keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public Book getById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbc.queryForObject("select * from books where id = :id", params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
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
        // noinspection ConstantConditions
        return jdbc.queryForObject("select count(*) from books", new HashMap<>(), Integer.class);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @RequiredArgsConstructor
    private class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int authorId = rs.getInt("author");
            Author author = authorDao.getById(authorId);
            int genreId = rs.getInt("genre");
            Genre genre = genreDao.getById(genreId);
            return new Book(id, title, author, genre);
        }
    }
}
