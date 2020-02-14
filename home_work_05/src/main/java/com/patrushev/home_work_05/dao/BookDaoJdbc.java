package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import com.patrushev.home_work_05.model.Book;
import com.patrushev.home_work_05.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public void insert(Book book) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("id", book.getId());
        params.put("title", book.getTitle());
        params.put("author", book.getAuthor().getId());
        params.put("genre", book.getGenre().getId());
        jdbc.update("insert into books (id, title, author, genre) values (:id, :title, :author, :genre)", params);
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select * from books where id = :id", params, new BookDaoJdbc.BookMapper(authorDao, genreDao));
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public int count() {
        return 0;
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {
        private final AuthorDao authorDao;
        private final GenreDao genreDao;

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
